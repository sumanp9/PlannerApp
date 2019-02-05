package com.pleasedo.planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class welcome extends AppCompatActivity {
    private static FloatingActionMenu menu;
    private static FloatingActionButton floatingEvent,floatingNote;

//asdasdasd
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final String note = "Note";
        final String event ="Event";

        floatingEvent = (FloatingActionButton)findViewById(R.id.floatingEvent);
        floatingNote = (FloatingActionButton)findViewById(R.id.floatingNote);

        floatingNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(welcome.this,Calender_Activity.class);
                intent.putExtra("note",note);
                startActivity(intent);
            }
        });

        floatingEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(welcome.this,Calender_Activity.class);
                intent.putExtra("note",event);
                startActivity(intent);
            }
        });
    }
}
