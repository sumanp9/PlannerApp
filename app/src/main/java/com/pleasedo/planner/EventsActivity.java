package com.pleasedo.planner;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventsActivity extends AppCompatActivity {
    @BindView(R.id.editDate)
    TextView editDate;
    @BindView(R.id.txtEventName)
    EditText txtEventName;
    @BindView(R.id.floatingBack)
    FloatingActionButton floatingBack;
    @BindView(R.id.floatingSaveEvent)
    FloatingActionButton floatingSaveEvent;
    @BindView(R.id.editTime)
    TextView editTime;

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private TimePickerDialog.OnTimeSetListener onTimeSetListner;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);
        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dateset = month + "/" + day + "/" + year;
                editDate.setText(dateset);
            }
        };

        onTimeSetListner = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                if (hour > 12){
                    String timeset =  hour-12+ ":"+minute+ " PM";
                    editTime.setText(timeset);
                }
                else if (hour <12){
                    String timeset =  hour+ ":"+minute+ " AM";
                    editTime.setText(timeset);
                }
                else if (hour ==12){
                    String timeset =  hour-12+ ":"+minute+ " AM";
                    editTime.setText(timeset);

                }

            }
        };
    }

    @OnClick({R.id.floatingBack, R.id.floatingSaveEvent,R.id.editDate, R.id.editTime})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floatingBack:
                onBackPressed();
                break;
            case R.id.floatingSaveEvent:
                onSavePressed();
                break;

            case R.id.editDate:
                onDateClicked();
                break;
            case R.id.editTime:
                onTimeClicked();
                break;
        }
    }

    public void onDateClicked() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        //int time  = cal.get(Calendar.HOUR)

        DatePickerDialog dialog = new DatePickerDialog(
                EventsActivity.this,
                android.R.style.Theme_DeviceDefault_Dialog,
                onDateSetListener,
                year, month, day);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        dialog.show();
    }



    private void onTimeClicked() {
        Calendar cal = Calendar.getInstance();

        int hour  = cal.get(Calendar.HOUR);
        int mins =  cal.get(Calendar.MINUTE);
        int am_pm = cal.get(Calendar.AM_PM);


        TimePickerDialog dialog = new TimePickerDialog(EventsActivity.this,
                android.R.style.Theme_DeviceDefault_Dialog,
                onTimeSetListner,hour,mins,true);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        dialog.show();
    }

    private void onSavePressed() {
    }



}
