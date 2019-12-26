package com.example.noteapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewMyNote extends AppCompatActivity {

    String id;
    ConstraintLayout constraintLayout;
    TextView date,title,txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_my_note);
        getSupportActionBar().hide();
        constraintLayout=findViewById(R.id.viewMyNoteLayout);
        date=findViewById(R.id.date);
        title=findViewById(R.id.Title);
        txt=findViewById(R.id.Txt);

        id=getIntent().getStringExtra("id");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase database=FirebaseDatabase.getInstance();
        String path = "User/"+user.getUid()+"/Book";
        DatabaseReference ref = database.getReference(path);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot bookSnapshot:dataSnapshot.getChildren()){
                    for (DataSnapshot noteSnapshot:bookSnapshot.child("Note").getChildren()){
                        String noteId=noteSnapshot.child("id").getValue().toString();
                        if (noteId.equals(id)){

                            int mark=noteSnapshot.child("mark").getValue(Integer.class);
                            String dt=noteSnapshot.child("date").getValue(String.class);
                            String ttl=noteSnapshot.child("title").getValue(String.class);
                            String tx=noteSnapshot.child("txt").getValue(String.class);

                            constraintLayout.setBackgroundColor(mark);
                            date.setText(dt);
                            title.setText(ttl);
                            txt.setText(tx);

                        }
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
