package com.example.gamebacklog;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class GameViewModel extends AndroidViewModel {
    private GameRepository gameRepository;
    private LiveData<List<Game>> gameList;

    public GameViewModel(Application application){
        super(application);
        gameRepository = new GameRepository(application);
        gameList = gameRepository.getAllGames();
    }

    public void insert(Game game){
        gameRepository.insert(game);
    }
    public void update(Game game){
        gameRepository.update(game);
    }
    public void delete(Game game){
        gameRepository.delete(game);
    }
    public void deleteAllGames(Game game) {  gameRepository.deleteAllGames(game);}
    public LiveData<List<Game>> getAllGames(){
        return gameList;
    }
}
