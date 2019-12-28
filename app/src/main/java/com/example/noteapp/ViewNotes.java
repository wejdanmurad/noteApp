package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewNotes extends AppCompatActivity implements NoteItemClickListener{

    List<Note> notes;
    NoteAdapter noteAdapter;
    ImageView imageView;
    TextView textView;
    String id;
    RecyclerView noteRV;
    SearchView search;
    List<Note> ntSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        getSupportActionBar().hide();
        id=getIntent().getStringExtra("id");

        imageView=findViewById(R.id.image_view);
        textView=findViewById(R.id.textView3);

        notes=new ArrayList<>();

        noteRV=findViewById(R.id.RVNote);


        ntSearch=new ArrayList<>();

        search=findViewById(R.id.searsh);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchNote(s);
                return false;
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        String path = "User/"+user.getUid()+"/Book/"+id+"/Note";
        DatabaseReference ref = database.getReference(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                notes.clear();

                for (DataSnapshot noteSnapshot:dataSnapshot.getChildren()){
                    String id=noteSnapshot.child("id").getValue().toString();
                    int mark=noteSnapshot.child("mark").getValue(Integer.class);
                    String date=noteSnapshot.child("date").getValue(String.class);
                    String title=noteSnapshot.child("title").getValue(String.class);
                    String txt=noteSnapshot.child("txt").getValue(String.class);

                    Note note=new Note(id,mark,date,title,txt);
                    notes.add(note);

                }
                setMyAdapter(notes);

                if (notes.size()==0){
                    imageView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);
                }else {
                    imageView.setVisibility(View.GONE);
                    textView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void addNote(View view) {
        Intent intent=new Intent(this,AddNote.class);
        intent.putExtra("id", id);
        startActivity(intent);
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent=new Intent(this,ViewMyNote.class);
        intent.putExtra("id",note.id);
        startActivity(intent);
    }

    public void Done(View view) {
        this.finish();
    }


    public void setMyAdapter(List<Note> nts){
        noteAdapter = new NoteAdapter(ViewNotes.this, nts, ViewNotes.this);
        noteRV.setAdapter(noteAdapter);
        noteRV.setLayoutManager(new LinearLayoutManager(ViewNotes.this, LinearLayoutManager.VERTICAL, false));
    }

    public void searchNote(String s){
        ntSearch.clear();
        for(Note note:notes){
            if (note.getTitle().contains(s)){
                ntSearch.add(note);
            }
        }
        setMyAdapter(ntSearch);
    }
}
