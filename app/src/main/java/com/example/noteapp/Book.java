package com.example.noteapp;

import java.util.List;

class Book {

    private int id;
    private int img;
    private String title;
    private List<Note> bookNotes;

    public Book(int id, int img, String title) {
        this.id = id;
        this.img = img;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Note> getBookNotes() {
        return bookNotes;
    }

    public void setBookNotes(List<Note> bookNotes) {
        this.bookNotes = bookNotes;
    }
}
