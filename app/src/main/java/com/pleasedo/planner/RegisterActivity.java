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

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void checkUsername() {

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String>userArray;
                userArray = newHandler.userList();
                String userName = UserName.getText().toString();
                String pass = Password.getText().toString();
                String rePass = RetypePassword.getText().toString();
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

                    Boolean check = validChecks(userName,pass,rePass,fName, lName, email);

                    if (check){
                        newHandler.addProduct(login);


                        Toast.makeText(RegisterActivity.this, "Congrats "+userName, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }

                }
            }
        });
    }

    private Boolean validChecks(String userName, String pass,String rePass, String fName, String lName, String email) {

        if (userName.equals("")||pass.equals("")||rePass.equals("")||fName.equals("")||lName.equals("")||email.equals("")){
            Toast.makeText(this, "Please fill in the empty box", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            if (pass.equals(rePass)){
                return true;
            }
            else{
                Toast.makeText(this, "Password does not match. Please retype password", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
}
