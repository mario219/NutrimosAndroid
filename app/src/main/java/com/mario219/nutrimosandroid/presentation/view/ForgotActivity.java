package com.mario219.nutrimosandroid.presentation.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mario219.nutrimosandroid.R;
import com.mario219.nutrimosandroid.presentation.presenter.ForgotPresenter;
import com.mario219.nutrimosandroid.presentation.view.contract.ForgotView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotActivity extends AppCompatActivity implements ForgotView{

    /**
     * UI
     */
    @BindView(R.id.etEmail_forgot)
    EditText tfEmailForgot;
    @BindView(R.id.progressBar_forgot)
    ProgressBar progressBar_forgot;

    /**
     * State
     */
    private ForgotPresenter forgotPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
        forgotPresenter = new ForgotPresenter(this);
    }

    @OnClick(R.id.btnBack_forgot)
    public void back(){
        finish();
    }

    @OnClick(R.id.btnResetPass_forgot)
    public void resetPassword(){

        String email = tfEmailForgot.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplication(), R.string.emailEmpty, Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar_forgot.setVisibility(View.VISIBLE);

        forgotPresenter.resetPassword(tfEmailForgot.getText().toString());

    }

    //View contract methods
    @Override
    public void resetSuccessful() {
        Toast.makeText(ForgotActivity.this, R.string.reset_successful_message, Toast.LENGTH_SHORT).show();
        progressBar_forgot.setVisibility(View.VISIBLE);
    }

    @Override
    public void resetFailed() {
        Toast.makeText(ForgotActivity.this, R.string.reset_failed_message, Toast.LENGTH_SHORT).show();
        progressBar_forgot.setVisibility(View.VISIBLE);
    }
}
