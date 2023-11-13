package com.example.RedSet.LogIn_SignUp_Pass;

public class LoginSignupException extends Exception{
    String text;

    public LoginSignupException(String text) {
        this.text = text;
    }
    public String toString(){
        return this.getClass().getCanonicalName() + ": Problem Occurs with " + text;
    }
}
