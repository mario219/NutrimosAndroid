package com.mario219.nutrimosandroid.presentation.view.contract;

/**
 * Created by marioalejndro on 5/05/17.
 */

public interface SignupView {

    void loadCurrentUser();
    void onSignupCompleted(Boolean task);
    void onSignupFailed(String exception);
    void onSignupSuccessful();

}
