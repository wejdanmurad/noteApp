package com.example.noteapp.signInUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.noteapp.R;
import com.example.noteapp.signInUp.SignIn;
import com.example.noteapp.signInUp.SignUp;

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
