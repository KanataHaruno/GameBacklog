package com.example.gamebacklog;

import android.app.Application;

import androidx.lifecycle.LiveData;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GameRepository {

    private GameRoomDatabase gameRoomDatabase;
    private GameDao gameDao;
    private LiveData<List<Game>> gameList;
    private Executor executor = Executors.newSingleThreadExecutor();

    public GameRepository(Context context) {
        gameRoomDatabase = GameRoomDatabase.getDatabase(context);
        gameDao = gameRoomDatabase.gameDao();
        gameList = gameDao.getAllGames();
    }

    // Methods that can be used in other classes


    public void insert(final Game game) {
        executor.execute((new Runnable() {
            @Override
            public void run() {
                gameDao.insert(game);
            }
        }));
    }

    public void update(final Game game) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                gameDao.update(game);
            }
        });
    }

    public void delete(final Game game) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                gameDao.delete(game);
            }
        });
    }

    public LiveData<List<Game>> getAllGames() {
        return gameList;
    }

    public void deleteAllGames(final Game game) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                gameDao.deleteAllGames();
            }
        });
    }
}
