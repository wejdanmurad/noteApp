package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewBooks extends AppCompatActivity implements BookItemClickListener {

    List<Book> books;
    List<Book> bkSearch;
    BookAdapter bookAdapter;
    RecyclerView bookRV;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_books);
        getSupportActionBar().hide();

        books=new ArrayList<>();
        bkSearch=new ArrayList<>();
        bookRV=findViewById(R.id.booksRe);

        search=findViewById(R.id.searsh);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchBook(s);
                return false;
            }
        });

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
                for (DataSnapshot bookSnapshot:dataSnapshot.getChildren()){
                    String id=bookSnapshot.child("id").getValue().toString();
                    int img=bookSnapshot.child("img").getValue(Integer.class);
                    String title=bookSnapshot.child("title").getValue(String.class);
                    Book book=new Book(id,img,title);
                    books.add(book);
                }
                setMyAdapter(books);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBookClick(Book book) {
        Intent intent = new Intent(this, ViewNotes.class);
        intent.putExtra("id", book.getId());
        startActivity(intent);
    }

    public void setMyAdapter(List<Book> bks){
        bookAdapter = new BookAdapter(ViewBooks.this, bks, ViewBooks.this);
        bookRV.setAdapter(bookAdapter);
        bookRV.setLayoutManager(new GridLayoutManager(ViewBooks.this, 3));
    }

    public void searchBook(String s){
        bkSearch.clear();
        for(Book book:books){
            if (book.getTitle().contains(s)){
                bkSearch.add(book);
            }
        }
        setMyAdapter(bkSearch);
    }
}
