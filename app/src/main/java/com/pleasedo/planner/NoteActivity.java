package com.pleasedo.planner;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NoteActivity extends AppCompatActivity {


    @BindView(R.id.floatingBack2)
    FloatingActionButton floatingBack2;
    @BindView(R.id.floatingSaveNote)
    FloatingActionButton floatingSaveNote;
    @BindView(R.id.notesTV)
    TextView notesTV;
    @BindView(R.id.noteTitleET)
    EditText noteTitleET;
    @BindView(R.id.noteDescMT)
    EditText noteDescMT;
    @BindView(R.id.noteDateTv)
    TextView noteDateTv;
    String userName ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        userName =  getIntent().getStringExtra("username");
        

    }

    @OnClick({R.id.floatingBack2, R.id.floatingSaveNote, R.id.noteDateTv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floatingBack2:
                onBackPressed();
                break;
            case R.id.floatingSaveNote:
                onSavePressed();
                break;
            case R.id.noteDateTv:
                onDateClicked();
                break;
        }
    }

    private void onDateClicked() {
    }

    private void onSavePressed() {
    }
}
