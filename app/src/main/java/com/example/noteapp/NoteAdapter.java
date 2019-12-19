package com.example.noteapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.MyViewHolder> {

    Context context;
    List<Note> mData;
    NoteItemClickListener Listener;

    public NoteAdapter(Context context, List<Note> mData, NoteItemClickListener Listener) {
        this.context = context;
        this.mData = mData;
        this.Listener=Listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.note,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.mark.setBackgroundColor(mData.get(position).getMark());
        holder.date.setText(mData.get(position).getDate());
        holder.TvTitle.setText(mData.get(position).getTitle());
        holder.TvTxt.setText(mData.get(position).getTxt());


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private View mark;
        private TextView date;
        private TextView TvTitle;
        private TextView TvTxt;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mark=itemView.findViewById(R.id.mark);
            date=itemView.findViewById(R.id.note_date);
            TvTitle=itemView.findViewById(R.id.noteTitle);
            TvTxt=itemView.findViewById(R.id.noteTxt);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Listener.onNoteClick(mData.get(getAdapterPosition()));
                }
            });

        }
    }
}
