package com.example.noteapp.signInUp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.noteapp.MainActivity;
import com.example.noteapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class Tutorial extends AppCompatActivity {


    private ViewPager sliderPager;
    private TabLayout indicator;
    private SlidePagerAdapter adapter;
    private List<Slide> listSlides;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        getSupportActionBar().hide();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user!=null){
            this.finishAndRemoveTask();
            startActivity(new Intent(Tutorial.this , MainActivity.class));
        }else{

            sliderPager = findViewById(R.id.slider_pager);
            indicator = findViewById(R.id.indicator);


            listSlides = new ArrayList<>(5);
            Slide slide1 = new Slide(R.drawable.tutorial, "Notebooks", getResources().getString(R.string.slid1Txt));
            Slide slide2 = new Slide(R.drawable.tutorial2, "Add Notes to Notebook", getResources().getString(R.string.slide2Txt));
            listSlides.add(slide1);
            listSlides.add(slide2);

            adapter = new SlidePagerAdapter(this, listSlides);
            sliderPager.setAdapter(adapter);

            indicator.setupWithViewPager(sliderPager, true);


        }

    }

    public void Skip(View view) {
        startActivity(new Intent(this, Splash.class));
    }

    public void Next(View view) {
        if (sliderPager.getCurrentItem() < listSlides.size() - 1) {
            sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
        }else{
            startActivity(new Intent(this, Splash.class));
        }
    }
}
