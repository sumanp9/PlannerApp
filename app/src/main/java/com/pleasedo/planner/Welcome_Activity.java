package com.pleasedo.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

//Team Members: Anisha Shrestha,Suman Pradhan
public class Welcome_Activity extends AppCompatActivity {

    private static FloatingActionMenu menu;
    private  static FloatingActionButton floatingEvent, floatingNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        floatingEvent = (FloatingActionButton)findViewById(R.id.floatingEvent);
        floatingNote = (FloatingActionButton)findViewById(R.id.floatingNote);

        floatingNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Activity.this, MainActivity.class);
                intent.putExtra("note","Note");
            }
        });

        floatingEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Welcome_Activity.this, MainActivity.class);
                intent.putExtra("note","Event");
            }
        });



    }



}
