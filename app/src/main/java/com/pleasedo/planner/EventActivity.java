package com.pleasedo.planner;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.github.clans.fab.FloatingActionMenu;

public class EventActivity extends AppCompatActivity {

    private static EditText editDate,editTitle;
    private FloatingActionButton floatingCancel, flaotingSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        String selectedDate =  getIntent().getStringExtra("date");

        mapPalette();

        //added focus on editText
        editTitle.setFocusableInTouchMode(true);
        editTitle.requestFocus();
        //Pre added date
        editDate.setText(selectedDate);

        floatingCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishActivity(0);
                Intent intent = new Intent(EventActivity.this, welcome.class);
                startActivity(intent);

            }
        });

        //Hello, this is just for testing purpose


    }

    private void mapPalette() {
        editDate = (EditText) findViewById(R.id.editDate);
        editTitle = (EditText)findViewById(R.id.editTitle);
        floatingCancel = (FloatingActionButton)findViewById(R.id.floatingCancel);
        flaotingSave = (FloatingActionButton)findViewById(R.id.floatingSave);
    }
}
