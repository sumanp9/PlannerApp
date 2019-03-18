package com.pleasedo.planner;

import android.content.Intent;
import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pleasedo.StoredData.Prefeneces;
import com.pleasedo.databases.loginDB;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private  EditText user, pass,box;
    private  Button btnLogin,btnRegister,btnForgot;

    loginDB dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHandler = new loginDB(this,null,null,1);

        user = (EditText)findViewById(R.id.UsernamePT);
        pass = (EditText)findViewById(R.id.Password);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnForgot =findViewById(R.id.btnForgot);

        box = findViewById(R.id.box);

        if (!Prefeneces.ifExists(this)){
            signIn();
        }

        loginPressed();
        registerPressed();
        forgotPressed();


    }

    private boolean getUsers(String userName, String password) {
        ArrayList<String>userArray = new ArrayList<String>();
        userArray =  dbHandler.userList();
        if (userArray.contains(userName)){
            if(dbHandler.getPassword(userName,password)){
                return true;
            }

        }
        return false;
    }

    private void signIn() {
        if (!Prefeneces.getUsername(this).equals("")) {
            String username = Prefeneces.getUsername(this);
            String pass = Prefeneces.getPassword(this);
            if (getUsers(username,pass)){
                Intent intent = new Intent(this, welcome.class);
                intent.putExtra("Username", username);
                startActivity(intent);
                finish();
            }

        }
    }

    private void forgotPressed() {
        btnForgot.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void registerPressed() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loginPressed() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (user.getText().toString().isEmpty()){
                    user.setError("Username cannot be empty");
                }
                else if (pass.getText().toString().isEmpty()){
                    pass.setError("Password field cannot be empty");
                }
                else {
                    checkUserNameandPassword();
                }
            }
        });
    }

    private void checkUserNameandPassword() {

        ArrayList<String>userArray = new ArrayList<String>();
        String userName =  user.getText().toString();
        String password = pass.getText().toString();
        userArray =  dbHandler.userList();
        box.setText(dbHandler.print());
        if (userArray.contains(userName)){
            if(dbHandler.getPassword(userName,password)){
                Prefeneces.saveUserData(userName,password,this);
                Toast.makeText(this, "Successful Login", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, welcome.class);
                intent.putExtra("Username", userName);
                startActivity(intent);
                finish();
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
