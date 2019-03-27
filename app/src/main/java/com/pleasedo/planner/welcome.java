package com.pleasedo.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.pleasedo.StoredData.Prefeneces;
import com.pleasedo.databases.userActivityDB;
import com.pleasedo.dbClass.Event;
import com.pleasedo.userDataAdapter;

import java.io.File;
import java.util.ArrayList;

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
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    userActivityDB dbHandler;
    //@BindView(R.id.txtExample)
    //TextView txtExample

    //Event Arrays for RecyclerView
    public ArrayList<String> eTitle = new ArrayList<>();
    public ArrayList<String> eDescription = new ArrayList<>();
    public ArrayList<String> eStartDate = new ArrayList<>();
    public ArrayList<String> eEndDate = new ArrayList<>();
    public ArrayList<String> eTime = new ArrayList<>();



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
            }
        });

        //dbpath is : /data/data/com.pleasedo.planner/databases

        databaseExists(dbHandler, userName);

    }

    private void databaseExists(userActivityDB dbHandler, String userName) {
        File dbFile = this.getDatabasePath("/data/data/com.pleasedo.planner/databases/" + userName + ".db");
        if (dbFile.exists()) {
            Toast.makeText(this, "Database exists for this user" + dbHandler, Toast.LENGTH_LONG).show();
            makeEventArrays();

            // txtExample.setText(events.get(0).getEventTime());
        } else {
            Toast.makeText(this, "Database Does not exist. Creating new database", Toast.LENGTH_SHORT).show();
        }
    }

    private void makeEventArrays() {
        ArrayList<Event> events = dbHandler.getName();

        int arraySize = events.size();
        for (int position = 0; position < arraySize; position++) {
            eTitle.add(events.get(position).getEventTitle());
            eDescription.add(events.get(position).getEventDetails());
            eStartDate.add(events.get(position).getEventStartDate());
            eEndDate.add(events.get(position).getEventEndDate());
            eTime.add(events.get(position).getEventTime());
        }

        initRecyclerView(eTitle, eDescription, eStartDate, eEndDate, eTime);
    }

    private void initRecyclerView(ArrayList<String> eTitle, ArrayList<String> eDescription, ArrayList<String> eStartDate, ArrayList<String> eEndDate, ArrayList<String> eTime) {
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        userDataAdapter adapter = new userDataAdapter(this, eTitle, eDescription, eStartDate, eEndDate, eTime);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    @OnClick(R.id.floatingLogout)
    public void onViewClicked() {
        Prefeneces.saveUserData("", "", this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
