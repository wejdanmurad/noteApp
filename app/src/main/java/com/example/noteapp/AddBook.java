package com.example.noteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class AddBook extends AppCompatActivity  implements View.OnClickListener {
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
    ImageView img;
    int imgR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        getSupportActionBar().hide();
        findViewById(R.id.title).requestFocus();
        createBoteSheeteDialog();

        img=findViewById(R.id.image_view);
        imgR=R.drawable.book1;


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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.oval1:
                img.setImageResource(R.drawable.book1);
                imgR=R.drawable.book1;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval2:
                img.setImageResource(R.drawable.book6);
                imgR=R.drawable.book6;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval3:
                img.setImageResource(R.drawable.book3);
                imgR=R.drawable.book3;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval4:
                img.setImageResource(R.drawable.book5);
                imgR=R.drawable.book5;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval5:
                img.setImageResource(R.drawable.book29);
                imgR=R.drawable.book29;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval6:
                img.setImageResource(R.drawable.book7);
                imgR=R.drawable.book7;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval7:
                img.setImageResource(R.drawable.book22);
                imgR=R.drawable.book22;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval8:
                img.setImageResource(R.drawable.book11);
                imgR=R.drawable.book11;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval9:
                img.setImageResource(R.drawable.book30);
                int imgR=R.drawable.book30;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval10:
                img.setImageResource(R.drawable.book8);
                imgR=R.drawable.book8;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval11:
                img.setImageResource(R.drawable.book23);
                imgR=R.drawable.book23;
                bottomSheetDialog.dismiss();
                break;
            case R.id.oval12:
                img.setImageResource(R.drawable.book25);
                imgR=R.drawable.book25;
                bottomSheetDialog.dismiss();
                break;


        }
    }

    public void show(View view) {
        bottomSheetDialog.show();
    }

    public void cancel(View view) {
        setResult(RESULT_CANCELED);
        this.finishAndRemoveTask();
    }

    public void save(View view) {
        Intent intent = new Intent();
        EditText title=findViewById(R.id.title);
        String titleTxt=title.getText().toString();
        intent.putExtra("title",titleTxt);
        intent.putExtra("img",imgR);
        setResult(RESULT_OK, intent);
        this.finishAndRemoveTask();

    }
}
