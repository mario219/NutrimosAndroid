package com.mario219.nutrimosandroid.presentation.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
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

    private static final String TAG = SignupActivity.class.getSimpleName();
    /**
     * UI
     */
    @BindView(R.id.etEmail_su)
    EditText su_tfEmail;
    @BindView(R.id.etPassword_su)
    EditText su_tfPassword;
    @BindView(R.id.progressBar_su)
    ProgressBar progressBar_su;

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

    @OnClick(R.id.btnSignUp_su)
    public void signup() {
        String email = su_tfEmail.getText().toString().trim();
        String password = su_tfPassword.getText().toString().trim();

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

        progressBar_su.setVisibility(View.VISIBLE);

        signupPresenter.createUser(email, password);
    }

    @OnClick(R.id.btnForgot_su)
    public void forgot(){
        startActivity(new Intent(SignupActivity.this, ForgotActivity.class));
    }

    @OnClick(R.id.btnSignIn_su)
    public void signin(){
        startActivity(new Intent(SignupActivity.this, SigninActivity.class));
        finish();
    }


    //view contract methods
    @Override
    public void loadCurrentUser() {
        startActivity(new Intent(SignupActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onSignupCompleted(Boolean task) {
        progressBar_su.setVisibility(View.GONE);
    }

    @Override
    public void onSignupFailed(String exception) {
        Toast.makeText(SignupActivity.this, R.string.auth_failed + " " + exception, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignupSuccessful() {
        startActivity(new Intent(SignupActivity.this, SigninActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar_su.setVisibility(View.GONE);
    }

}
