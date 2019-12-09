package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_note);
        createBoteSheeteDialog();
        ImageView picker = findViewById(R.id.picker);
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
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval2:
                bottomSheetDialog.dismiss();

                break;
            case R.id.oval3:
                bottomSheetDialog.dismiss();

                break;
            case R.id.oval4:
                bottomSheetDialog.dismiss();

                break;
            case R.id.oval5:
                bottomSheetDialog.dismiss();

                break;
            case R.id.oval6:
                bottomSheetDialog.dismiss();

                break;
            case R.id.oval7:
                bottomSheetDialog.dismiss();

                break;
            case R.id.oval8:
                bottomSheetDialog.dismiss();

                break;
            case R.id.oval9:
                bottomSheetDialog.dismiss();

                break;
            case R.id.oval10:
                bottomSheetDialog.dismiss();

                break;
            case R.id.oval11:
                bottomSheetDialog.dismiss();

                break;
            case R.id.oval12:
                bottomSheetDialog.dismiss();

                break;


        }
    }
}
