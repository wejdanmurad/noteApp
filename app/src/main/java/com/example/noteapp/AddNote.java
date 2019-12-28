package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class AddNote extends AppCompatActivity implements View.OnClickListener {
    BottomSheetDialog bottomSheetDialog;
    ImageView b1;
    ImageView b2;
    ImageView b3;
    ImageView b4;
    ImageView b5;
    ImageView b6;
    ImageView b7;
    ImageView b8;
    ImageView b9;
    ImageView b10;
    ImageView b11;
    ImageView b12;
    ConstraintLayout layout;
    int color;
    EditText title, txt;
    TextView date;
    String bookId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_note);
        createBoteSheeteDialog();

        layout=findViewById(R.id.noteLayout);


        date=findViewById(R.id.date);
        title=findViewById(R.id.Title);
        txt=findViewById(R.id.Txt);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour12hrs = calendar.get(Calendar.HOUR);
        int minutes = calendar.get(Calendar.MINUTE);

        String d=day+"/"+month+"/"+year+"     "+hour12hrs+":"+minutes;
        date.setText(d);

        color=getResources().getColor(R.color.color1);


        bookId=getIntent().getStringExtra("id");

    }

    private void createBoteSheeteDialog() {
        if (bottomSheetDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.bottom_sheet_layout, null);
            bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(view);
            b1 = view.findViewById(R.id.oval1);
            b2 = view.findViewById(R.id.oval2);
            b3 = view.findViewById(R.id.oval3);
            b4 = view.findViewById(R.id.oval4);
            b5 = view.findViewById(R.id.oval5);
            b6 = view.findViewById(R.id.oval6);
            b7 = view.findViewById(R.id.oval7);
            b8 = view.findViewById(R.id.oval8);
            b9 = view.findViewById(R.id.oval9);
            b10 = view.findViewById(R.id.oval10);
            b11 = view.findViewById(R.id.oval11);
            b12 = view.findViewById(R.id.oval12);
            b1.setOnClickListener(this);
            b2.setOnClickListener(this);
            b3.setOnClickListener(this);
            b4.setOnClickListener(this);
            b5.setOnClickListener(this);
            b6.setOnClickListener(this);
            b7.setOnClickListener(this);
            b8.setOnClickListener(this);
            b9.setOnClickListener(this);
            b10.setOnClickListener(this);
            b11.setOnClickListener(this);
            b12.setOnClickListener(this);

        }
    }

    public void showDialog(View view) {
        bottomSheetDialog.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oval1:
                color=getResources().getColor(R.color.color1);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval2:
                color=getResources().getColor(R.color.color2);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval3:
                color=getResources().getColor(R.color.color3);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval4:
                color=getResources().getColor(R.color.color4);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval5:
                color=getResources().getColor(R.color.color5);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval6:
                color=getResources().getColor(R.color.color6);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval7:
                color=getResources().getColor(R.color.color7);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval8:
                color=getResources().getColor(R.color.color8);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval9:
                color=getResources().getColor(R.color.color9);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval10:
                color=getResources().getColor(R.color.color10);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval11:
                color=getResources().getColor(R.color.color11);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval12:
                color=getResources().getColor(R.color.color12);
                layout.setBackgroundColor(color);
                bottomSheetDialog.dismiss();
                break;


        }
    }

    public void saveNote(View view) {

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        String id = FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Book").child(bookId).child("Note").push().getKey();
        Note note=new Note(id,color,date.getText().toString(),title.getText().toString(),txt.getText().toString());
        FirebaseDatabase.getInstance().getReference().child("User").child(user.getUid()).child("Book").child(bookId).child("Note").child(String.valueOf(id)).setValue(note);


        this.finishAndRemoveTask();
    }
}
