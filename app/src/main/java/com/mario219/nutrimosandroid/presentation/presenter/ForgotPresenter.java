package com.mario219.nutrimosandroid.presentation.presenter;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.mario219.nutrimosandroid.presentation.view.contract.ForgotView;

/**
 * Created by marioalejndro on 11/05/17.
 */

public class ForgotPresenter {

    private ForgotView view;
    private FirebaseAuth auth;

    public ForgotPresenter(ForgotView view) {

        this.view = view;
        auth = FirebaseAuth.getInstance();

    }

    public void resetPassword(String email){
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {
                            view.resetSuccessful();
                        } else {
                            view.resetFailed();
                        }

                    }
                });
    }
}
