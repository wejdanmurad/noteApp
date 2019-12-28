package com.example.noteapp.signInUp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noteapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    EditText emailPass;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        getSupportActionBar().hide();
        emailPass=findViewById(R.id.passworEmail);
        firebaseAuth=firebaseAuth.getInstance();

    }

    public void EmailPass(View view) {

        String email=emailPass.getText().toString().trim();

        if (email.equals("")){
            Toast.makeText(this, "Please enter your EMAIL !!", Toast.LENGTH_LONG).show();
        }else{
            firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    if (task.isSuccessful()){
                        Intent intent=new Intent(ForgetPassword.this,CheackEmail.class);
                        finishAndRemoveTask();
                        startActivity(intent);
                    }else{
                        Toast.makeText(ForgetPassword.this, "Please make sure your EMAIL is a real one !!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }
}
