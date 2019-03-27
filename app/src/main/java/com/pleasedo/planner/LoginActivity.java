package com.pleasedo.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pleasedo.StoredData.Prefeneces;
import com.pleasedo.databases.loginDB;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.btnForgot)
    Button btnForgot;
    @BindView(R.id.UsernamePT)
    EditText user;
    @BindView(R.id.Password)
    EditText pass;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.imageView)
    ImageView imageView;


    loginDB dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        dbHandler = new loginDB(this, null, null, 1);



        if (!Prefeneces.ifExists(this)) {
            signIn();
        }

        loginPressed();
        registerPressed();
        forgotPressed();


    }

    private boolean getUsers(String userName, String password) {
        ArrayList<String> userArray = new ArrayList<String>();
        userArray = dbHandler.userList();
        if (userArray.contains(userName)) {
            if (dbHandler.getPassword(userName, password)) {
                return true;
            }

        }
        return false;
    }

    private void signIn() {
        if (!Prefeneces.getUsername(this).equals("")) {
            String username = Prefeneces.getUsername(this);
            String pass = Prefeneces.getPassword(this);
            if (getUsers(username, pass)) {
                Intent intent = new Intent(this, welcome.class);
                intent.putExtra("Username", username);
                startActivity(intent);
                finish();
            }

        }
    }

    private void forgotPressed() {
        btnForgot.setOnClickListener(new View.OnClickListener() {

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

                if (user.getText().toString().isEmpty()) {
                    user.setError("Username cannot be empty");
                } else if (pass.getText().toString().isEmpty()) {
                    pass.setError("Password field cannot be empty");
                } else {
                    checkUserNameandPassword();
                }
            }
        });
    }

    private void checkUserNameandPassword() {

        ArrayList<String> userArray = new ArrayList<String>();
        String userName = user.getText().toString();
        String password = pass.getText().toString();
        userArray = dbHandler.userList();
        if (userArray.contains(userName)) {
            if (dbHandler.getPassword(userName, password)) {
                Prefeneces.saveUserData(userName, password, this);
                Toast.makeText(this, "Successful Login", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, welcome.class);
                intent.putExtra("Username", userName);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Username or password incorrect", Toast.LENGTH_LONG).show();
            }


        } else {
            Toast.makeText(this, "username does not exists", Toast.LENGTH_LONG).show();
        }
    }
}
