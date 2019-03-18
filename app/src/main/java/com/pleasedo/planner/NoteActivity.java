package com.pleasedo.planner;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pleasedo.databases.userActivityDB;
import com.pleasedo.dbClass.Note;

import java.util.Calendar;

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
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private userActivityDB dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        ButterKnife.bind(this);
        userName +=  getIntent().getStringExtra("username");

        dbHandler = new userActivityDB(this,userName,null,1);

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dateset = month + "/" + day + "/" + year;
                noteDateTv.setText(dateset);
            }
        };

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
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = null;
        //int time  = cal.get(Calendar.HOUR)



            dialog = new DatePickerDialog(
                    NoteActivity.this,
                    android.R.style.Theme_DeviceDefault_Dialog,
                    onDateSetListener,
                    year, month, day);


        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        dialog.show();
    }

    private void onSavePressed() {
        String noteTitle =  noteTitleET.getText().toString();
        String noteDetails =  noteDescMT.getText().toString();
        String noteDate =  noteDateTv.getText().toString();

        if (noteTitle.isEmpty() || noteDetails.isEmpty() || noteDate.equals("Note Date")){
            Toast.makeText(this, "Please fill all the required fields", Toast.LENGTH_SHORT).show();
        }
        else{
            Note newNote =  new Note(noteTitle, noteDetails,noteDate);
            dbHandler.addNote(newNote);
            Toast.makeText(this, "Note created for"+ userName, Toast.LENGTH_SHORT).show();

            clearFields();

        }
    }

    private void clearFields() {
        noteDateTv.setText("Note Date");
        noteDescMT.setText("");
        noteTitleET.setText("");
    }
}
