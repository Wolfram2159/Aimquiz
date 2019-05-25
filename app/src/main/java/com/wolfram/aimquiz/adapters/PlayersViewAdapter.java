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
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.glide.RequestBuilderFactory;
import com.wolfram.aimquiz.tools.ItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Wolfram
 * @date 2019-03-27
 */
public class PlayersViewAdapter extends RecyclerView.Adapter<PlayersViewAdapter.RecyclerViewHolder> {
    private List<Player> playerList;
    private ItemClickListener itemClickListener;
    private RequestBuilderFactory requestFactory;

    public PlayersViewAdapter(List<Player> playerList, ItemClickListener itemClickListener, Context context) {
        this.playerList = playerList;
        this.itemClickListener = itemClickListener;
        requestFactory = new RequestBuilderFactory(context);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        private ImageView imageView;
        private ProgressBar progressBar;

        //todo: make private, was public
        RecyclerViewHolder(View myView) {
            super(myView);
            myView.setOnClickListener(this);
            textView = myView.findViewById(R.id.player_text_view);
            imageView = myView.findViewById(R.id.player_image_view);
            progressBar = myView.findViewById(R.id.player_progress_bar);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    @NonNull
    @Override
    public PlayersViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.players_view_row, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewAdapter.RecyclerViewHolder holder, int position) {
        Player player = playerList.get(position);
        holder.textView.setText(player.getNick());
        holder.progressBar.setVisibility(View.VISIBLE);
        RequestBuilder request = requestFactory.getPlayerRequestBuilder(player.getPlayerHLTV(), new RequestListener<Drawable>() {
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
        return playerList.size();
    }

    @Override
    public long getItemId(int position) {
        return playerList.get(position).getId();
    }
}
