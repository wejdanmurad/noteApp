package com.example.noteapp.signInUp;

public class Slide {
    private int Image;
    private String Title;
    private String Txt;

    public Slide(int image, String title, String txt) {
        Image = image;
        Title = title;
        Txt = txt;
    }

    public String getTxt() {
        return Txt;
    }

    public void setTxt(String txt) {
        Txt = txt;
    }

    public int getImage() {
        return Image;
    }

    public String getTitle() {
        return Title;
    }

    public void setImage(int image) {
        Image = image;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
