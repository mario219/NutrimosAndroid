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
import com.mario219.nutrimosandroid.presentation.presenter.SigninPresenter;
import com.mario219.nutrimosandroid.presentation.view.contract.SigninView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SigninActivity extends AppCompatActivity implements SigninView {

    /**
     * UI
     */
    @BindView(R.id.etEmail_si)
    EditText si_tfEmail;
    @BindView(R.id.etPassword_si)
    EditText si_tfPassword;
    @BindView(R.id.progressBar_si)
    ProgressBar progressBar_si;

    /**
     * State
     */
    private SigninPresenter signinPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        ButterKnife.bind(this);
        signinPresenter = new SigninPresenter(this);
    }


    @OnClick(R.id.btnSignIn_si)
    public void signin(){

        if (TextUtils.isEmpty(si_tfEmail.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.emailEmpty, Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(si_tfPassword.getText().toString())) {
            Toast.makeText(getApplicationContext(), R.string.passwordEmpty, Toast.LENGTH_SHORT).show();
            return;
        }

        if (si_tfPassword.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), R.string.passwordtooshort, Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar_si.setVisibility(View.VISIBLE);

        signinPresenter.signinrequest(si_tfEmail.getText().toString(), si_tfPassword.getText().toString());

    }

    @OnClick(R.id.btnForgot_si)
    public void forgot(){
        startActivity(new Intent(SigninActivity.this, ForgotActivity.class));
    }

    @OnClick(R.id.btnSignUp_si)
    public void signup(){
        startActivity(new Intent(SigninActivity.this, SignupActivity.class));
        finish();
    }

    //View contract methods
    @Override
    public void getSession() {
        startActivity(new Intent(SigninActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onSigninRequestSuccess() {
        progressBar_si.setVisibility(View.GONE);
    }

    @Override
    public void onSigninRequestFailure() {
        Toast.makeText(SigninActivity.this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSigninRequestSuccessful() {
        Intent intent = new Intent(SigninActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
