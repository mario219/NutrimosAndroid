package com.mario219.nutrimosandroid.presentation.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.mario219.nutrimosandroid.R;
import com.mario219.nutrimosandroid.presentation.presenter.SignupPresenter;
import com.mario219.nutrimosandroid.presentation.view.contract.SignupView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignupActivity extends AppCompatActivity implements SignupView{

    /**
     * UI
     */
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.btnSignUp)
    Button btnSignUp;
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.btnForgot)
    Button btnForgot;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private SignupPresenter signupPresenter;

    /**
     *State
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        signupPresenter = new SignupPresenter(this);

    }

    @Override
    public void initializeView() {
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignupActivity.this, ForgotActivity.class));
            }
        });
    }

    @Override
    public void signup() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}
