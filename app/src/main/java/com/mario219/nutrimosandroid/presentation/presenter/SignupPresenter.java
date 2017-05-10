package com.mario219.nutrimosandroid.presentation.presenter;

import android.app.Instrumentation;
import android.support.annotation.NonNull;
import android.test.InstrumentationTestRunner;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mario219.nutrimosandroid.R;
import com.mario219.nutrimosandroid.presentation.view.contract.SignupView;

import java.util.concurrent.Executor;


/**
 * Created by marioalejndro on 5/05/17.
 */

public class SignupPresenter {

    private static final String TAG = SignupPresenter.class.getSimpleName();
    private SignupView view;
    private FirebaseAuth auth;

    public SignupPresenter(SignupView view) {

        this.view = view;
        this.auth = FirebaseAuth.getInstance();

    }

    public void createUser(String email, String password) {

        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Boolean isSuccessful = task.isSuccessful();
                        view.onSignupCompleted(isSuccessful);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.i(TAG, R.string.auth_failed + " " + task.getException());
                            view.onSignupFailed();
                        } else {
                            view.onSignupSuccessful();
                        }
                    }
                });

    }

}
