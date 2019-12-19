package com.example.noteapp;

import android.app.ActivityOptions;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookItemClickListener,NoteItemClickListener {
    static final int MY_REQUEST_CODE = 10;
    List<Book> books;
    List<Note> notes;
    BookAdapter bookAdapter;
    NoteAdapter noteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        books=new ArrayList<>();
        books.add(new Book(0,R.drawable.addbook,"Create Notebook"));

        bookAdapter = new BookAdapter(this, books, this);
        RecyclerView bookRV=findViewById(R.id.bookRV);
        bookRV.setAdapter(bookAdapter);
        bookRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        notes=new ArrayList<>();

        noteAdapter = new NoteAdapter(this, notes, this);
        RecyclerView noteRV=findViewById(R.id.notesRV);
        noteRV.setAdapter(noteAdapter);
        noteRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));



    }

    @Override
    public void onBookClick(Book book) {
        if (book.getId()==0){
            startActivityForResult(new Intent(this,AddBook.class),MY_REQUEST_CODE);
        }else {
            Intent intent = new Intent(this, ViewNotes.class);
            intent.putExtra("id", book.getId());
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String title=data.getStringExtra("title");
                int img = data.getIntExtra("img", -1);
                int id=books.size();
                books.add(new Book(id,img,title));
                bookAdapter.notifyDataSetChanged();
            }
        }
    }

    public void showAll(View view) {
        Intent intent=new Intent(this,ViewBooks.class);
        startActivity(intent);
    }

    public void showNotes(View view) {
        Intent intent=new Intent(this,ViewNotes.class);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(Note note) {

    }
}
