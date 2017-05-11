package com.mario219.nutrimosandroid.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mario219.nutrimosandroid.presentation.view.contract.MainView;


/**
 * Created by marioalejndro on 11/05/17.
 */

public class MainPresenter {


    private MainView view;
    private FirebaseAuth auth;
    private static final String TAG = MainView.class.getSimpleName();

    public MainPresenter(MainView view) {

        this.view = view;
        auth = FirebaseAuth.getInstance();
    }

    public void changePassword(String email) {

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            view.emailsent();
                        } else {
                            view.failure();
                        }
                    }
                });

    }

    public void signOutCurrentUser(){

        auth.signOut();

        // this listener will be called when there is change in firebase user session
        FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    view.signOutUser();
                }

            }
        };

    }
}
