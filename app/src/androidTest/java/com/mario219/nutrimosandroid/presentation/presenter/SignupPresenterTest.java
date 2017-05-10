package com.mario219.nutrimosandroid.presentation.presenter;

import com.mario219.nutrimosandroid.presentation.view.contract.SignupView;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by marioalejndro on 5/05/17.
 */
public class SignupPresenterTest {

    @Test
    public void shouldPass(){
        Assert.assertEquals(1,1);
    }

    @Test
    public void shouldSendParamsToFirebase(){

        //given
        SignupView view = new MockView();

        //when
        SignupPresenter signUpPresenter = new SignupPresenter(view);

        //then

    }

    private class MockView implements SignupView {

        @Override
        public void onSignupCompleted(Boolean task) {

        }

        @Override
        public void onSignupFailed() {

        }

        @Override
        public void onSignupSuccessful() {

        }
    }

}