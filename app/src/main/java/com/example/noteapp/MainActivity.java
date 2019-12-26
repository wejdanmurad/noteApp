package com.example.noteapp;

import android.app.ActivityOptions;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.noteapp.signInUp.Tutorial;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookItemClickListener,NoteItemClickListener {

    List<Book> books;
    List<Note> notes;
    BookAdapter bookAdapter;
    NoteAdapter noteAdapter;
    RecyclerView bookRV;
    RecyclerView noteRV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        books=new ArrayList<>();
        books.add(new Book("add",R.drawable.addbook,"Create Notebook"));
        bookRV=findViewById(R.id.bookRV);

        notes=new ArrayList<>();
        noteRV=findViewById(R.id.notesRV);


    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        String path = "User/"+user.getUid()+"/Book";
        DatabaseReference ref = database.getReference(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                books.clear();
                notes.clear();
                books.add(new Book("add",R.drawable.addbook,"Create Notebook"));

                for (DataSnapshot bookSnapshot:dataSnapshot.getChildren()){
                    String id=bookSnapshot.child("id").getValue().toString();
                    for (DataSnapshot noteSnapshot:bookSnapshot.child("Note").getChildren()){
                                String noteId=noteSnapshot.child("id").getValue().toString();
                                int mark=noteSnapshot.child("mark").getValue(Integer.class);
                                String date=noteSnapshot.child("date").getValue(String.class);
                                String title=noteSnapshot.child("title").getValue(String.class);
                                String txt=noteSnapshot.child("txt").getValue(String.class);

                                Note note=new Note(noteId,mark,date,title,txt);
                                notes.add(note);
                            }

//                    String notePath=path+"/"+id+"/Note";
//                    DatabaseReference noteRef = database.getReference(notePath);
//                    noteRef.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(@NonNull DataSnapshot Snapshot) {
//
//
//                            for (DataSnapshot noteSnapshot:Snapshot.getChildren()){
//                                String id=noteSnapshot.child("id").getValue().toString();
//                                int mark=noteSnapshot.child("mark").getValue(Integer.class);
//                                String date=noteSnapshot.child("date").getValue(String.class);
//                                String title=noteSnapshot.child("title").getValue(String.class);
//                                String txt=noteSnapshot.child("txt").getValue(String.class);
//
//                                Note note=new Note(id,mark,date,title,txt);
//                                notes.add(note);
//                            }
//                        }
//                        @Override
//                        public void onCancelled(@NonNull DatabaseError databaseError) {}
//                    });

                    int img=bookSnapshot.child("img").getValue(Integer.class);
                    String title=bookSnapshot.child("title").getValue(String.class);
                    Book book=new Book(id,img,title);
                    books.add(book);
                }
                bookAdapter = new BookAdapter(MainActivity.this, books, MainActivity.this);
                bookRV.setAdapter(bookAdapter);
                bookRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false));

                noteAdapter = new NoteAdapter(MainActivity.this, notes, MainActivity.this);
                noteRV.setAdapter(noteAdapter);
                noteRV.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBookClick(Book book) {
        if (book.getId().equals("add")){
            Intent intent = new Intent(this, AddBook.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, ViewNotes.class);
            intent.putExtra("id", book.getId());
            startActivity(intent);
        }
    }


    public void showAll(View view) {
        Intent intent=new Intent(this,ViewBooks.class);
        startActivity(intent);
    }

    public void showNotes(View view) {
        Intent intent=new Intent(this,ShowAllNotes.class);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent=new Intent(this,ViewMyNote.class);
        intent.putExtra("id",note.id);
        startActivity(intent);
    }

    public void SignOut(View view) {
        FirebaseAuth.getInstance().signOut();
        finishAndRemoveTask();
        startActivity(new Intent(this, Tutorial.class));
    }
}
