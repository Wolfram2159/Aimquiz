package com.wolfram.aimquiz.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.database.Team;
import com.wolfram.aimquiz.tools.ItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Wolfram
 * @date 2019-03-28
 */
public class TeamsViewAdapter extends RecyclerView.Adapter<TeamsViewAdapter.TeamsViewHolder> {
    private List<Team> teamList;
    private ItemClickListener listener;

    public TeamsViewAdapter(List<Team> teamList, ItemClickListener listener) {
        this.teamList = teamList;
        this.listener = listener;
    }

    public class TeamsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;

        public TeamsViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textView = itemView.findViewById(R.id.my_text_view);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        TeamsViewHolder vh = new TeamsViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull TeamsViewHolder holder, int position) {
        holder.textView.setText(teamList.get(position).toString());
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
