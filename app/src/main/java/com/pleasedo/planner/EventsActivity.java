package com.pleasedo.planner;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.pleasedo.databases.userActivityDB;
import com.pleasedo.dbClass.Event;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    @BindView(R.id.editEndDate)
    TextView editEndDate;

    @BindView(R.id.editDescription)
    EditText editDescription;

    private String name ="";
    private userActivityDB dbHandler;

    private DatePickerDialog.OnDateSetListener onDateSetListener;
    private DatePickerDialog.OnDateSetListener onEndDateSetListener;
    private TimePickerDialog.OnTimeSetListener onTimeSetListner;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);

        name+=getIntent().getStringExtra("username");
        dbHandler = new userActivityDB(this,name,null,1);

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dateset = month+1 + "/" + day + "/" + year;
                editDate.setText(dateset);
            }
        };

        onEndDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                String dateset = month+1 + "/" + day + "/" + year;
                editEndDate.setText(dateset);

            }
        };

        onTimeSetListner = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                String timeset = "";
                if (hour > 12) {
                    if (minute >=10) {
                        timeset+= hour - 12 + ":" + minute + " PM";
                    }
                    else{
                        timeset+= hour - 12 + ":" +"0" +minute + " PM";

                    }

                    editTime.setText(timeset);
                } else if (hour < 12) {
                    if (minute >=10) {
                        timeset = hour + ":" + minute + " AM";
                    }
                    else{
                        timeset = hour + ":" + "0"+minute + " AM";

                    }
                    editTime.setText(timeset);
                } else if (hour == 12) {
                    if (minute >=10) {
                        timeset = hour - 12 + ":" + minute + " AM";
                    }
                    else
                    {
                        timeset = hour - 12 + ":" + "0"+minute + " AM";

                    }
                    editTime.setText(timeset);

                }

            }
        };
    }

    @OnClick({R.id.floatingBack, R.id.floatingSaveEvent,
            R.id.editDate, R.id.editTime, R.id.editEndDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.floatingBack:
                onBackPressed();
                break;
            case R.id.floatingSaveEvent:
                onSavePressed();
                break;

            case R.id.editDate:
                onDateClicked("Start");
                break;
            case R.id.editTime:
                onTimeClicked();
                break;
            case R.id.editEndDate:
                onDateClicked("End"); //need to work on this
                break;

        }
    }

    public void onDateClicked(String type) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = null;
        //int time  = cal.get(Calendar.HOUR)

        if (type.equals("Start")){

            dialog = new DatePickerDialog(
                    EventsActivity.this,
                    android.R.style.Theme_DeviceDefault_Dialog,
                    onDateSetListener,
                    year, month, day);
        }
        else if (type.equals("End"))
        {
            dialog = new DatePickerDialog(
                    EventsActivity.this,
                    android.R.style.Theme_DeviceDefault_Dialog,
                    onEndDateSetListener,
                    year, month, day);
        }

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        dialog.show();
    }


    private void onTimeClicked() {
        Calendar cal = Calendar.getInstance();

        int hour = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(EventsActivity.this,
                android.R.style.Theme_DeviceDefault_Dialog,
                onTimeSetListner, hour, mins, true);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
        dialog.show();
    }

    private void onSavePressed()  {

        String startDate =  editDate.getText().toString();
        String endDate =  editEndDate.getText().toString();
        String title =  txtEventName.getText().toString();
        String time = editTime.getText().toString();
        String details  = editDescription.getText().toString();


        if (startDate.equals("Event Start Date") || endDate.equals("Event End Date") ||
                time.equals("Event Time") || title.isEmpty() || details.isEmpty()){
            Toast.makeText(this, "Please fill all the required fields to create event", Toast.LENGTH_SHORT).show();

        }

        else {
            //this checks if the endDate enteredby the user is smaller than the startDate
            if (check2Dates(endDate, startDate)) {
                Event e = new Event();
                e.setEventTitle(title);
                e.setEventDetails(details);
                e.setEventEndDate(endDate);
                e.setEventStartDate(startDate);
                e.setEventTime(time);

                dbHandler.addEvent(e);

                Toast.makeText(this, "Event created", Toast.LENGTH_SHORT).show();
                clearFields();
            }
            else {
                Toast.makeText(EventsActivity.this, "Please select a valid date", Toast.LENGTH_SHORT).show();
            }


    }
        }

    private void clearFields() {
        txtEventName.setText("");
        editDescription.setText("");
        editDate.setText("Event Start Date");
        editEndDate.setText("Event End Date");
        editTime.setText("Event Time");




    }

    private boolean check2Dates(String endDate, String startDate)  {

        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);

        Calendar dateEnd = Calendar.getInstance();
        Calendar dateStart = Calendar.getInstance();

        try {
            dateEnd.setTime(sdf.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            dateStart.setTime(sdf.parse(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int eYear = dateEnd.get(Calendar.YEAR);
        int eDay = dateEnd.get(Calendar.DAY_OF_MONTH);
        int eMonth = dateEnd.get(Calendar.MONTH);

        int sYear = dateStart.get(Calendar.YEAR);
        int sDay = dateStart.get(Calendar.DAY_OF_MONTH);
        int sMonth = dateStart.get(Calendar.MONTH);


        if ((eYear >= sYear && eMonth >= sMonth && eDay >= sDay)) {

            //what if month is larger but date is smaller

            return true;

        }
        else if (eYear >= sYear && eMonth >= sMonth ){
            return true;

        }
        else{
            return false;

        }

    }


}





