package com.mario219.nutrimosandroid.presentation.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mario219.nutrimosandroid.R;
import com.mario219.nutrimosandroid.presentation.presenter.MainPresenter;
import com.mario219.nutrimosandroid.presentation.view.contract.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName();
    /**
     * UI
     */

    @BindView(R.id.main_etEmail)
    EditText tfEmail;
    @BindView(R.id.main_progressBar)
    ProgressBar main_progressBar;

    /**
     * State
     */
    private MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // [START handle_data_extras]
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }
        mainPresenter = new MainPresenter(this);
    }

    @OnClick(R.id.main_btnSend)
    public void sendPasswordReset(){

        main_progressBar.setVisibility(View.VISIBLE);
        if (!tfEmail.getText().toString().trim().equals("")) {
            mainPresenter.changePassword(tfEmail.getText().toString());
        } else {
            Toast.makeText(this, R.string.emailEmpty, Toast.LENGTH_SHORT).show();
            main_progressBar.setVisibility(View.GONE);
        }

    }

    @OnClick(R.id.main_btnSignOut)
    public void signOut(){
        mainPresenter.signOutCurrentUser();
    }

    @OnClick(R.id.main_btnSubscribe)
    public void subscribeToTopic(){
        mainPresenter.subscribeTopic();
    }


    //View contract methods

    @Override
    public void emailsent() {
        Toast.makeText(MainActivity.this, R.string.emailSent, Toast.LENGTH_SHORT).show();
        main_progressBar.setVisibility(View.GONE);
    }

    @Override
    public void failure() {
        Toast.makeText(MainActivity.this, R.string.emailNotSent, Toast.LENGTH_SHORT).show();
        main_progressBar.setVisibility(View.GONE);
    }

    @Override
    public void signOutUser() {
        startActivity(new Intent(MainActivity.this, SigninActivity.class));
        finish();
    }

    @Override
    public void subscribe() {
        Toast.makeText(MainActivity.this, R.string.subscription_successful, Toast.LENGTH_SHORT).show();
    }
}
