package com.example.noteapp.signInUp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.noteapp.R;
import com.example.noteapp.signInUp.Slide;

import java.util.List;

public class SlidePagerAdapter extends PagerAdapter {

    private Context mContext;
    private List<Slide> mList;

    public SlidePagerAdapter(Context mContext, List<Slide> mList){
        this.mContext=mContext;
        this.mList=mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View slideLayout=inflater.inflate(R.layout.slide_item,null);

        ImageView slideImg=slideLayout.findViewById(R.id.Splash_img);
        TextView slideTitle=slideLayout.findViewById(R.id.Splash_title);
        TextView slideText=slideLayout.findViewById(R.id.Splash_txt);

        slideImg.setImageResource(mList.get(position).getImage());
        slideTitle.setText(mList.get(position).getTitle());
        slideText.setText(mList.get(position).getTxt());

        container.addView(slideLayout);
        return slideLayout;




    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object ;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View)object);
    }
}
