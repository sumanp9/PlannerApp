package com.pleasedo.planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pleasedo.databases.loginDB;
import com.pleasedo.dbClass.Login;

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
        newHandler = new loginDB(this,null,null,1);

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
                ArrayList<String>userArray;
                userArray = newHandler.userList();
                String userName = UserName.getText().toString();
                String pass = Password.getText().toString();
                String fName = FirstName.getText().toString();
                String lName = LastName.getText().toString();
                String email = Email.getText().toString();
                if (userArray.contains(userName)){
                    Toast.makeText(RegisterActivity.this, "Username already exists.", Toast.LENGTH_SHORT).show();
                    UserName.setText("");
                    Password.setText("");
                    RetypePassword.setText("");

                }
                else{
                    Login login =  new Login(userName,fName,lName,email,pass);
                    newHandler.addProduct(login);

                    Toast.makeText(RegisterActivity.this, "Congrats "+userName, Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                }

            }
        });
    }
}
