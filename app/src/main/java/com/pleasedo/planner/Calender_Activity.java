package com.pleasedo.planner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Date;

public class Calender_Activity extends AppCompatActivity {

    private static CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        calendarView = (CalendarView)findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = month+1+"/"+dayOfMonth+"/"+year ;
                //Hint: convert String selectedDate to type Date and compaare
                //it with the today's date. If the selectedDate is lesser or in the past then
                //allow user to select the date again. if not proceed as normal.
                Intent intent = new Intent(Calender_Activity.this, MainActivity.class);
                intent.putExtra("date",selectedDate);
                startActivity(intent);
            }
        });


    }
}
