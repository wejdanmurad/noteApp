package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spla);
        getSupportActionBar().hide();
    }


    public void signIn(View view) {
        //this.finish();
        startActivity(new Intent(this, SignIn.class));
    }

    public void singUp(View view) {
        //this.finish();
        startActivity(new Intent(this, SignUp.class));
    }
}
