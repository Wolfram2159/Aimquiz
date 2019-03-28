package com.wolfram.aimquiz.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wolfram.aimquiz.R;
import com.wolfram.aimquiz.database.Player;
import com.wolfram.aimquiz.tools.ItemClickListener;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Wolfram
 * @date 2019-03-27
 */
public class PlayersViewAdapter extends RecyclerView.Adapter<PlayersViewAdapter.RecyclerViewHolder> {
    private List<Player> playerList;
    private ItemClickListener mClickListener;

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView textView;

        public RecyclerViewHolder(View myView) {
            super(myView);
            myView.setOnClickListener(this);
            textView = myView.findViewById(R.id.my_text_view);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) {
                mClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public PlayersViewAdapter(List<Player> playerList, ItemClickListener itemClickListener) {
        this.playerList = playerList;
        this.mClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public PlayersViewAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersViewAdapter.RecyclerViewHolder holder, int position) {
        holder.textView.setText(playerList.get(position).toString());
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
