package com.example.noteapp.signInUp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.noteapp.MainActivity;
import com.example.noteapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity {

    EditText emailEt , passwordEt;
    Button signUpBtn;
    String email,password;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        emailEt = findViewById(R.id.email_sig_in);
        passwordEt = findViewById(R.id.passworEmail);
        signUpBtn = findViewById(R.id.signInBtn);
        signUpBtn.setOnClickListener(v->{
            signUpBtn.setEnabled(false);
            email = emailEt.getText().toString();
            password = passwordEt.getText().toString();

            if (email.isEmpty() && password.isEmpty()) {
                Toast.makeText(SignIn.this, "fill the fields", Toast.LENGTH_SHORT).show();
                emailEt.setError("please enter email");
                passwordEt.setError("please enter password");
                emailEt.requestFocus();
                signUpBtn.setEnabled(true);
            } else if (email.isEmpty()) {
                emailEt.setError("please enter email");
                emailEt.requestFocus();
                signUpBtn.setEnabled(true);
            } else if (password.isEmpty()) {
                passwordEt.setError("please enter password");
                passwordEt.requestFocus();
                signUpBtn.setEnabled(true);
            } else {
                doSignIn(email, password);
            }
        });
    }

    public void signUp(View view) {
        this.finishAndRemoveTask();
        startActivity(new Intent(this, SignUp.class));
    }

    public void finish(View view) {
        this.finishAndRemoveTask();
    }

    private void doSignIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        FirebaseUser user = mAuth.getCurrentUser();


                        Map<String,Object> data = new HashMap<>();
                        data.put("lastSignIn",new Date().getTime());


                        FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).updateChildren(data)
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(SignIn.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                        Log.d("error",e.getLocalizedMessage());
                                    }
                                })
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Intent intent = new Intent(SignIn.this , MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        signUpBtn.setEnabled(true);
                                    }
                                });

                    } else {
                        Toast.makeText(SignIn.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        signUpBtn.setEnabled(true);
                    }

                });
    }

    public void ForgetPass(View view) {
        Intent intent=new Intent(this,ForgetPassword.class);
        startActivity(intent);
    }
}
