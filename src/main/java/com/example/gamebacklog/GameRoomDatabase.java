package com.example.gamebacklog;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;

import android.content.Context;
import android.os.AsyncTask;

@Database(entities = {Game.class}, version = 1)
public abstract class GameRoomDatabase extends RoomDatabase {

    public abstract GameDao gameDao();
    private final static String NAME_DATABASE = "game_database";
    private static volatile GameRoomDatabase INSTANCE;


    public static GameRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (GameRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            GameRoomDatabase.class, NAME_DATABASE).build();
                }
            }
        }

        return INSTANCE;

    }
}
