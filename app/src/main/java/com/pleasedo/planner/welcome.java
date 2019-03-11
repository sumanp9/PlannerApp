package com.pleasedo.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.pleasedo.StoredData.Prefeneces;

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
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.txtWelcome)
    TextView txtWelcome;
    @BindView(R.id.floatingLogout)
    android.support.design.widget.FloatingActionButton floatingLogout;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);

        final String note = "Note";
        final String event = "Event";
        String userName = getIntent().getStringExtra("Username");

        txtWelcome.setText("Welcome " + userName + ",");
        floatingNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, Calender_Activity.class);
                intent.putExtra("note", note);
                startActivity(intent);
            }
        });

        floatingEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, EventsActivity.class);
                startActivity(intent);

                /*Intent intent =  new Intent(welcome.this,Calender_Activity.class);
                intent.putExtra("note",event);
                startActivity(intent);*/
            }
        });
    }

    @OnClick(R.id.floatingLogout)
    public void onViewClicked() {
        Prefeneces.saveUserData("","",this);
        Intent intent =  new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
