package com.mario219.nutrimosandroid.presentation.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
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
        setSupportActionBar(toolbar);
        initializeView();
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
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        });
    }

    @Override
    public void onSignupSuccessfull(Boolean task) {
        Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
        // If sign in fails, display a message to the user. If sign in succeeds
        // the auth state listener will be notified and logic to handle the
        // signed in user can be handled in the listener.
        if (!task.isSuccessful()) {
            Toast.makeText(SignupActivity.this, "Authentication failed." /* + task.getException() */,
                    Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(SignupActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

}
