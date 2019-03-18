package com.pleasedo.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.pleasedo.StoredData.Prefeneces;
import com.pleasedo.databases.userActivityDB;
import com.pleasedo.dbClass.Event;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class welcome extends AppCompatActivity {

    @BindView(R.id.floatingEvent)
    FloatingActionButton floatingEvent;
    @BindView(R.id.floatingNote)
    FloatingActionButton floatingNote;
    @BindView(R.id.menu)
    FloatingActionMenu menu;

    @BindView(R.id.txtWelcome)
    TextView txtWelcome;
    @BindView(R.id.floatingLogout)
    android.support.design.widget.FloatingActionButton floatingLogout;

    userActivityDB dbHandler;
    @BindView(R.id.txtExample)
    TextView txtExample;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        final String userName = getIntent().getStringExtra("Username");

        dbHandler = new userActivityDB(this, userName, null, 1);

        final String note = "Note";
        final String event = "Event";

        txtWelcome.setText("Welcome " + userName + ",");
        floatingNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, NoteActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);
            }
        });

        floatingEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, EventsActivity.class);
                intent.putExtra("username", userName);
                startActivity(intent);

                /*Intent intent =  new Intent(welcome.this,Calender_Activity.class);
                intent.putExtra("note",event);
                startActivity(intent);*/
            }
        });

        //dbpath is : /data/data/com.pleasedo.planner/databases
        File dbFile = this.getDatabasePath("/data/data/com.pleasedo.planner/databases/" + userName + ".db");
        if (dbFile.exists()) {
            Toast.makeText(this, "Database exists for this user" + dbHandler, Toast.LENGTH_LONG).show();
            String n = dbHandler.getName();
            txtExample.setText(n);
        } else {
            Toast.makeText(this, "Database Does not exist. Creating new database", Toast.LENGTH_SHORT).show();
            //createUsersdb();
        }

    }

    private void createUsersdb() {
        Event e = new Event();
        e.setEventTitle("asdas");
        e.setEventDetails("aasaa");
        e.setEventEndDate("2018-06-09");
        e.setEventStartDate("2018-06-02");
        e.setEventTime("10:44");

        dbHandler.addEvent(e);

    }

    @OnClick(R.id.floatingLogout)
    public void onViewClicked() {
        Prefeneces.saveUserData("", "", this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
