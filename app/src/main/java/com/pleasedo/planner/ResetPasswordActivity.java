package com.pleasedo.planner;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.textView6)
    TextView textView6;
    @BindView(R.id.txtEmail)
    EditText txtEmail;
    @BindView(R.id.btnReset)
    Button btnReset;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.textView7)
    TextView textView7;
    @BindView(R.id.imageView2)
    ImageView imageView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);


    }


    @OnClick({R.id.btnReset, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnReset:
                resetPressed();
                break;
            case R.id.btnLogin:
                loginPressed();
                break;
        }
    }
    private void loginPressed() {
        Intent intent = new Intent(ResetPasswordActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    private void resetPressed() {


    }




}