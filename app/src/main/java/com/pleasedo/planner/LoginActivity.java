package com.pleasedo.planner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private static EditText UserName, Password;
    private static Button Login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserName = (EditText)findViewById(R.id.UsernamePT);
        Password = (EditText)findViewById(R.id.Password);
        Login = (Button)findViewById(R.id.SigninBtn);
    }
}
