package com.pleasedo.planner;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



public class NoteActivity extends AppCompatActivity {
    FloatingActionButton CancelButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        CancelButton =(FloatingActionButton) findViewById(R.id.cancelFltBtn);

        CancelButton.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteActivity.this, welcome.class);
                startActivity(intent);
            }
        });

    }

}
