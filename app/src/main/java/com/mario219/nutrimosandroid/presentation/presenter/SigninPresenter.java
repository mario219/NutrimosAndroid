package com.mario219.nutrimosandroid.presentation.presenter;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.mario219.nutrimosandroid.presentation.view.contract.SigninView;

/**
 * Created by marioalejndro on 10/05/17.
 */

public class SigninPresenter {

    private SigninView view;
    private FirebaseAuth auth;

    public SigninPresenter(SigninView view) {
        this.view = view;
        this.auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null)
            view.getSession();
        auth = FirebaseAuth.getInstance();
    }

    public void signinrequest(String email, String password) {

        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        view.onSigninRequestSuccess();
                        if (!task.isSuccessful()) {
                            // there was an error
                            view.onSigninRequestFailure();
                        } else {
                            view.onSigninRequestSuccessful();
                        }
                    }
                });
    }
}
