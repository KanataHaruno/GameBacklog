package com.example.gamebacklog;

import android.app.Activity;
import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


import java.text.DateFormat;
import java.util.Calendar;

public class AddGame extends AppCompatActivity {

    private FloatingActionButton saveButton;

    private EditText title;
    private EditText platform;
    private Spinner statusSpinner;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);

        saveButton = findViewById(R.id.saveButton);
        title = findViewById(R.id.titleTextView);
        platform = findViewById(R.id.platformTextView);
        statusSpinner = findViewById(R.id.statusSpinner);

        // Array Adapter for spinner
        // status array is the list with the spinner options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status_array, android.R.layout.simple_spinner_item);
        // Which layout will be used when using spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        // Intent from other activity
        Intent intent = getIntent();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String saveTitle = title.getText().toString();
                String savePlatform = platform.getText().toString();
                String saveStatus = statusSpinner.getSelectedItem().toString();
                // Use calendar to find current date
                Calendar calendar = Calendar.getInstance();
                String saveDate = DateFormat.getDateInstance().format(calendar.getTime());


                Intent returnIntent = new Intent();
                returnIntent.putExtra("title", saveTitle);
                returnIntent.putExtra("platform", savePlatform);
                returnIntent.putExtra("status", saveStatus);
                returnIntent.putExtra("date", saveDate);
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            }
        });
    }


}
