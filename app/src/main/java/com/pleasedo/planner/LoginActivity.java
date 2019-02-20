package com.pleasedo.planner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pleasedo.databases.loginDB;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private  EditText UserName, Password,box;
    private  Button btnLogin,btnRegister,btnForgot;

    loginDB dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHandler = new loginDB(this,null,null,1);

        UserName = (EditText)findViewById(R.id.UsernamePT);
        Password = (EditText)findViewById(R.id.Password);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnForgot =findViewById(R.id.btnForgot);

        box = findViewById(R.id.box);

        loginPressed();
        registerPressed();
        forgotPressed();


    }

    private void forgotPressed() {
    }

    private void registerPressed() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginPressed() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUserNameandPassword();

            }
        });
    }

    private void checkUserNameandPassword() {

        ArrayList<String>userArray = new ArrayList<String>();
        String userName =  UserName.getText().toString();
        String password = Password.getText().toString();
        userArray =  dbHandler.userList();
        box.setText(dbHandler.print());
        if (userArray.contains(userName)){
            if(dbHandler.getPassword(userName,password)){
                Toast.makeText(this, "Successful Login", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(this, "Username or password incorrect", Toast.LENGTH_LONG).show();
            }


        }
        else
        {
            Toast.makeText(this, "username does not exists", Toast.LENGTH_LONG).show();
        }
    }
}
