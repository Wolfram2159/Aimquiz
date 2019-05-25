package com.wolfram.aimquiz.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.glide.RequestBuilderFactory;
import com.wolfram.aimquiz.tools.ItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Wolfram
 * @date 2019-03-28
 */
public class TeamsViewAdapter extends RecyclerView.Adapter<TeamsViewAdapter.TeamsViewHolder> {
    private List<Team> teamList;
    private ItemClickListener listener;
    private RequestBuilderFactory requestFactory;

    public TeamsViewAdapter(List<Team> teamList, ItemClickListener listener, Context context) {
        this.teamList = teamList;
        this.listener = listener;
        requestFactory = new RequestBuilderFactory(context);
    }
    //todo: try to remake this class to inner static, watch MainAcitvity
    public class TeamsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private ImageView imageView;
        private ProgressBar progressBar;
        TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = itemView.findViewById(R.id.team_text_view);
            imageView = itemView.findViewById(R.id.team_image_view);
            progressBar = itemView.findViewById(R.id.team_progress_bar);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teams_view_row, parent, false);
        TeamsViewHolder vh = new TeamsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        Team team = teamList.get(position);
        holder.textView.setText(team.getName());
        holder.progressBar.setVisibility(View.VISIBLE);

        RequestBuilder request = requestFactory.getTeamRequestBuilder(team.getTeamHLTV(), new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                holder.progressBar.setVisibility(View.GONE);
                return false;
            }
        });
        request.into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return teamList.size();
    }

    @Override
    public long getItemId(int position) {
        return teamList.get(position).getId();
    }
}
