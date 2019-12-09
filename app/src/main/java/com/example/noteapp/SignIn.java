package com.example.noteapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();
    }

    public void signUp(View view) {
        this.finishAndRemoveTask();
        startActivity(new Intent(this,SignUp.class));
    }

    public void finish(View view) {
        this.finishAndRemoveTask();
    }

}
