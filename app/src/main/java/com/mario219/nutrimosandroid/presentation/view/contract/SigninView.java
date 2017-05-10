package com.mario219.nutrimosandroid.presentation.view.contract;

/**
 * Created by marioalejndro on 10/05/17.
 */

public interface SigninView {
    void getSession();
    void onSigninRequestSuccess();
    void onSigninRequestFailure();
    void onSigninRequestSuccessful();
}
