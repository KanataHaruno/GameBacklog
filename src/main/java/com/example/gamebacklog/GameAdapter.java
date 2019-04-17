package com.example.gamebacklog;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {


    private List<Game> gameList = new ArrayList<>();


    GameAdapter(List<Game> data) {
        this.gameList = gameList;
    }


    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_item, parent, false);
        GameViewHolder holder = new GameViewHolder(view);
        return holder;
    }

    // Get game info by position
    public void onBindViewHolder(@NonNull GameAdapter.GameViewHolder holder, int position) {
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
        private CardView cardView;

        public GameViewHolder(final View itemView) {
            super(itemView);
            gameTitle = itemView.findViewById(R.id.gameTitleView);
            gamePlatform = itemView.findViewById(R.id.gamePlatformView);
            gameStatus = itemView.findViewById(R.id.gameStatusView);
            gameDate = itemView.findViewById(R.id.gameDateView);
            cardView = itemView.findViewById(R.id.cardView);
            cardView.setClickable(true);


        }
    }

}

