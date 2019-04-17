package com.example.gamebacklog;

import android.app.Activity;
import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {


    public static final String ITEM = "Game";
    private final static int ADD_REQUEST = 0000;
    private final static int UPDATE_REQUEST = 1111;

    private FloatingActionButton addGame;

    private GameAdapter gameAdapter;
    private RecyclerView recyclerView;
    private GameRoomDatabase gameDataBase;
    private Executor executor;
    private GameDao gameDao;
    private GameViewModel gameViewModel;
    private Game gameItem;
    private Game restoredGame;
    private List<Game> gameList;
    private List<Game> restoreGames;

    View.OnClickListener mRestoreAllGames;
    View.OnClickListener mRestoreGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameList = new ArrayList<>();
        restoreGames = new ArrayList<>();

        // Link recycler view to layout
        recyclerView = findViewById(R.id.recyclerview_game_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        recyclerView.setHasFixedSize(true);
        gameAdapter = new GameAdapter(gameList);
        recyclerView.setAdapter(gameAdapter);

        gameDataBase = GameRoomDatabase.getDatabase(this);
        executor = Executors.newSingleThreadExecutor();
        gameDao = gameDataBase.gameDao();


        // Fab for adding game
        addGame = findViewById(R.id.fabAddGame);
        addGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddGame.class);
                startActivityForResult(intent, 1);
            }
        });

        // Update UI when changes are made
        gameViewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        gameViewModel.getAllGames().observe(this, new Observer<List<Game>>() {
            @Override
            public void onChanged(@Nullable List<Game> games) {
                gameList = games;
                updateUI();
            }
        });


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            // Remove item when swiping left or right
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                int position = (viewHolder.getAdapterPosition());

                Snackbar.make(findViewById(android.R.id.content), (gameList.get(position)).
                        getTitle() + "deleted.", Snackbar.LENGTH_LONG).setAction(R.string.undo,
                        mRestoreGame).show();

                restoredGame = gameList.get(position);
                gameViewModel.delete(gameList.get(position));
                gameList.remove(position);
                gameAdapter.notifyItemRemoved(position);

            }

        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.addOnItemTouchListener(this);

        gameViewModel.getAllGames();

        mRestoreGame = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameViewModel.insert(restoredGame);
                updateUI();
            }
        };

        mRestoreAllGames = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < restoreGames.size(); i++) {
                    gameViewModel.insert(restoreGames.get(i));
                }
            }
        };

    }

    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode,data );
        if (requestCode == ADD_REQUEST && resultCode == RESULT_OK) {

                Game game = data.getParcelableExtra(MainActivity.ITEM);
                gameViewModel.insert(game);
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

        } else if (requestCode == UPDATE_REQUEST && resultCode == RESULT_OK){

            Game game = data.getParcelableExtra(MainActivity.ITEM);
            int id = game.getId();
            if (id ==-1){
                Toast.makeText(this,"There's a problem, item can't be saved" ,
                        Toast.LENGTH_SHORT);
            }
        } else {
            Toast.makeText(this, "Saving item failed!", Toast.LENGTH_SHORT);
        }


    }

    private void updateUI() {
        if (gameAdapter == null) {
            gameAdapter = new GameAdapter(gameList);
            recyclerView.setAdapter(gameAdapter);
        } else {
            gameAdapter.swapList(gameList);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
    }

}