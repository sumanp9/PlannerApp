package com.pleasedo.planner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {
    private static EditText FirstName, LastName,
            UserName, Email, Password, RetypePassword;
    private static Button Register;
    loginDB newHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        FirstName = (EditText) findViewById(R.id.FirstNameTV);
        LastName = (EditText) findViewById(R.id.LastNameTV);
        UserName = (EditText) findViewById(R.id.usernameTV);
        Email = (EditText) findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        RetypePassword = (EditText)findViewById(R.id.password2);
        Register = (Button)findViewById(R.id.registerBtn);

        checkUsername();
    }

    private void checkUsername() {

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String>userArray = new ArrayList<String>();
                userArray = newHandler.userList();
                String userName = UserName.getText().toString();
                if (userArray.contains(userName)){
                    Toast.makeText(RegisterActivity.this, "Username already exists.", Toast.LENGTH_SHORT).show();
                    UserName.setText("");
                    Password.setText("");
                    RetypePassword.setText("");
                }
            }
        });
    }
}
