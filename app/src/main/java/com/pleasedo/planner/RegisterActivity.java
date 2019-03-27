package com.pleasedo.planner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.pleasedo.databases.loginDB;
import com.pleasedo.dbClass.Login;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    loginDB newHandler;


    @BindView(R.id.FirstNameTV)
    EditText FirstName;
    @BindView(R.id.LastNameTV)
    EditText LastName;
    @BindView(R.id.usernameTV)
    EditText UserName;
    @BindView(R.id.email)
    EditText Email;
    @BindView(R.id.password)
    EditText Password;
    @BindView(R.id.password2)
    EditText RetypePassword;
    @BindView(R.id.registerBtn)
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        newHandler = new loginDB(this, null, null, 1);
        checkUsername();
    }



    private void checkUsername() {

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> userArray;
                userArray = newHandler.userList();
                String userName = UserName.getText().toString();
                String pass = Password.getText().toString();
                String rePass = RetypePassword.getText().toString();
                String fName = FirstName.getText().toString();
                String lName = LastName.getText().toString();
                String email = Email.getText().toString();
                if (userArray.contains(userName)) {
                    Toast.makeText(RegisterActivity.this, "Username already exists.", Toast.LENGTH_SHORT).show();
                    UserName.setText("");
                    Password.setText("");
                    RetypePassword.setText("");

                } else {
                    Login login = new Login(userName, fName, lName, email, pass);

                    Boolean check = validChecks(userName, pass, rePass, fName, lName, email);

                    if (check) {
                        newHandler.addProduct(login);


                        Toast.makeText(RegisterActivity.this, "Congrats " + userName, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();

                    }

                }
            }
        });
    }

    private Boolean validChecks(String userName, String pass, String rePass, String fName, String lName, String email) {

        if (userName.equals("") || pass.equals("") || rePass.equals("") || fName.equals("") || lName.equals("") || email.equals("")) {
            Toast.makeText(this, "Please fill in the empty box(s)", Toast.LENGTH_LONG).show();
            return false;
        } else {
            if (pass.equals(rePass)) {
                return true;
            } else {
                Toast.makeText(this, "Password does not match. Please retype password", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
    }
}
