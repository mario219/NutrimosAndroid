package com.mario219.nutrimosandroid.presentation.presenter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mario219.nutrimosandroid.presentation.view.SignupActivity;
import com.mario219.nutrimosandroid.presentation.view.contract.SignupView;

/**
 * Created by marioalejndro on 5/05/17.
 */

public class SignupPresenter {

    private SignupView view;
    private FirebaseAuth auth;

    public SignupPresenter(SignupView view) {

        this.view = view;
        this.auth = FirebaseAuth.getInstance();

    }

    public void createUser(String email, String password) {
        //create user
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Boolean onSuccessfulMessage = task.isSuccessful();
                        Toast.makeText(SignupActivity.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(SignupActivity.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            view.onSignupSuccessfull();
                        }
                    }
                });
    }
}
