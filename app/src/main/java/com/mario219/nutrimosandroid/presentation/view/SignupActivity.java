package com.mario219.nutrimosandroid.presentation.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mario219.nutrimosandroid.R;
import com.mario219.nutrimosandroid.presentation.presenter.SignupPresenter;
import com.mario219.nutrimosandroid.presentation.view.contract.SignupView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignupActivity extends AppCompatActivity implements SignupView{

    /**
     * UI
     */
    @BindView(R.id.etEmail)
    EditText etEmail;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    /**
     *State
     */
    private SignupPresenter signupPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        signupPresenter = new SignupPresenter(this);
    }

    @OnClick(R.id.btnSignUp)
    public void signup() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), R.string.emailEmpty, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), R.string.passwordEmpty, Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), R.string.passwordtooshort, Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        signupPresenter.createUser(email, password);
    }

    @OnClick(R.id.btnForgot)
    public void forgot(){
        startActivity(new Intent(SignupActivity.this, ForgotActivity.class));
    }

    @OnClick(R.id.btnSignIn)
    public void signin(){
        finish();
    }

    //view contract methods
    @Override
    public void onSignupCompleted(Boolean task) {
        Toast.makeText(SignupActivity.this, R.string.auth_completed + " " + task, Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSignupFailed() {
        Toast.makeText(SignupActivity.this, R.string.auth_failed, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignupSuccessful() {
        startActivity(new Intent(SignupActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}
