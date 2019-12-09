package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        getSupportActionBar().hide();
    }

    public void signIn(View view) {
        this.finishAndRemoveTask();
        startActivity(new Intent(this,SignIn.class));
    }

    public void finish(View view) {
        this.finishAndRemoveTask();
    }
}
