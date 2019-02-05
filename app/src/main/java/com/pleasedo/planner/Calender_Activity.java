package com.pleasedo.planner;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Calender_Activity extends AppCompatActivity {

    private static CalendarView calendarView;
    private static TextView txtViewHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);
        calendarView = (CalendarView)findViewById(R.id.calendarView);
        txtViewHeader=(TextView)findViewById(R.id.txtViewHeader);
        String note = getIntent().getStringExtra("note");
        txtViewHeader.setText("Please select a date to add a "+note);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = month+1+"/"+dayOfMonth+"/"+year ;

                //Hint: convert String selectedDate to type Date and compaare
                //it with the today's date. If the selectedDate is lesser or in the past then
                //allow user to select the date again. if not proceed as normal.
                Intent intent = new Intent(Calender_Activity.this, MainActivity.class);
                intent.putExtra("date",selectedDate);


                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

                Date today = new Date();
                Date userdate =  new Date();
                try {
                    userdate =sdf.parse(selectedDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }


                //Somehow this is working
                if ((userdate.getYear()>=today.getYear() && userdate.getMonth() >= today.getMonth() && userdate.getDate() >=today.getDate())){
                    startActivity(intent);

                }
               else{
                    Toast.makeText(Calender_Activity.this, "Please select a valid date", Toast.LENGTH_SHORT).show();

                }
            }
        });


    }
}
