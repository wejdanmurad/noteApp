package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowAllNotes extends AppCompatActivity implements NoteItemClickListener{

    List<Note> notes;
    NoteAdapter noteAdapter;
    RecyclerView noteRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_notes);
        getSupportActionBar().hide();

        notes=new ArrayList<>();
        noteRV=findViewById(R.id.notesRe);

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

                notes.clear();

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
                }

                noteAdapter = new NoteAdapter(ShowAllNotes.this, notes, ShowAllNotes.this);
                noteRV.setAdapter(noteAdapter);
                noteRV.setLayoutManager(new LinearLayoutManager(ShowAllNotes.this, LinearLayoutManager.VERTICAL, false));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onNoteClick(Note note) {
        Intent intent=new Intent(this,ViewMyNote.class);
        intent.putExtra("id",note.id);
        startActivity(intent);
    }
}
