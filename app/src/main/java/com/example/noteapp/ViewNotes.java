package com.example.noteapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

public class ViewNotes extends AppCompatActivity implements NoteItemClickListener{

    static final int MY_NOTE_CODE = 20;
    List<Note> notes;
    NoteAdapter noteAdapter;
    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);

        imageView=findViewById(R.id.image_view);
        textView=findViewById(R.id.textView3);

        notes=new ArrayList<>();

        noteAdapter = new NoteAdapter(this, notes, this);
        RecyclerView noteRV=findViewById(R.id.RVNote);
        noteRV.setAdapter(noteAdapter);
        noteRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        if (notes.size()==0){
            imageView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
        }else {
            imageView.setVisibility(View.GONE);
            textView.setVisibility(View.GONE);
        }
    }

    public void addNote(View view) {
        Intent intent=new Intent(this,AddNote.class);
        startActivityForResult(intent,MY_NOTE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_NOTE_CODE) {
            if (resultCode == RESULT_OK) {

                int mark = data.getIntExtra("mark", -1);
                String date=data.getStringExtra("date");
                String title=data.getStringExtra("title");
                String txt=data.getStringExtra("txt");

                int id=notes.size();
                notes.add(new Note(id,mark,date,title,txt));
                noteAdapter.notifyDataSetChanged();

                imageView.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);

            }
        }
    }


    @Override
    public void onNoteClick(Note note) {

    }

    public void Done(View view) {
        this.finish();
    }
}
