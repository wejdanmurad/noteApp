package com.example.noteapp.signInUp;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noteapp.R;

public class CheackEmail extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheack_email);
        getSupportActionBar().hide();
    }

    public void RecoverPass(View view) {
        finishAndRemoveTask();
    }

    public void close(View view) {
        finishAndRemoveTask();
    }
}
