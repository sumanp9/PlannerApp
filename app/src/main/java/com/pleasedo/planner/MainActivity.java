package com.pleasedo.planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static TextView textView;
    private static Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gettingDate();

    }

    private void gettingDate() {
        button = (Button)findViewById(R.id.button);
        textView = (TextView)findViewById(R.id.textView);
        String selectedDate = getIntent().getStringExtra("date");
        if (selectedDate!=null){
            textView.setText(selectedDate);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(MainActivity.this,Calender_Activity.class);
                startActivity(intent);
            }
        });
    }


}
