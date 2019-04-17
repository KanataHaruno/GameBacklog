package com.example.gamebacklog;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {


    private List<Game> gameList = new ArrayList<>();
    //private onItemClickListener listener;

    GameAdapter(List<Game> data) {
        this.gameList = gameList;
    }


    public GameViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View row = layoutInflater.inflate(R.layout.game_item, viewGroup, false);
        GameViewHolder viewHolder = new GameViewHolder(row);

        return viewHolder;
    }

    // Get game info by position
    public void onBindViewHolder(GameViewHolder holder, int position) {
        Game currentGame = gameList.get(position);

        holder.gameTitle.setText(currentGame.getTitle());
        holder.gamePlatform.setText(currentGame.getPlatform());
        holder.gameStatus.setText(currentGame.getStatus());
        holder.gameDate.setText(currentGame.getDate());
    }

    public int getItemCount() {
        return gameList.size();
    }

    public Game getItemAt(int position) {
        return gameList.get(position);
    }

    // When editing a game, adds notification when game is edited
    public void setGames(List<Game> gameList) {
        this.gameList = gameList;
        notifyDataSetChanged();
    }

    public void swapList(List<Game> newGameList){
        gameList = newGameList;
        if (newGameList!= null){
            this.notifyDataSetChanged();
        }
    }

    class GameViewHolder extends RecyclerView.ViewHolder {
        private TextView gameTitle;
        private TextView gamePlatform;
        private TextView gameStatus;
        private TextView gameDate;

        public GameViewHolder(final View itemView) {
            super(itemView);
            gameTitle = itemView.findViewById(R.id.gameTitleView);
            gamePlatform = itemView.findViewById(R.id.gamePlatformView);
            gameStatus = itemView.findViewById(R.id.gameStatusView);
            gameDate = itemView.findViewById(R.id.gameDateView);


        }
    }

}

