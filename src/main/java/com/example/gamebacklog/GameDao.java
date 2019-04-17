package com.example.gamebacklog;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface GameDao {

    @Insert
    void insert(Game game);

    @Update
    void update(Game game);

    @Delete
    void delete(Game game);

    // Can be found in the repository
    @Query("DELETE FROM game_table")
    void deleteAllGames();

    @Query("SELECT * FROM game_table")
    LiveData<List<Game>> getAllGames();

}