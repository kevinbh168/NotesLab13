package com.brena.noteslab13.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brena.noteslab13.R;
import com.brena.noteslab13.activities.MainActivity;
import com.brena.noteslab13.models.Note;
import com.brena.noteslab13.repositories.NoteRepository;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;
import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder>  {
    private List<Note> notes;
    private AppCompatActivity activity;


    public void setComments(List<Note> notes){
        this.notes= notes;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comments,viewGroup,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.ViewHolder viewHolder, int i) {
        final Note notes=this.notes.get(i);
        viewHolder.textTitulo.setText(notes.getTitle());
        viewHolder.textFecha.setText(String.valueOf(notes.getDate()));
        viewHolder.textComentario.setText(notes.getContent());
        final Context context=viewHolder.itemView.getContext();
        viewHolder.deleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoteRepository.deleteNote(notes.getId());
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitulo;
        private TextView textFecha;
        private TextView textComentario;
        private ImageButton deleteNote;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
             textTitulo=itemView.findViewById(R.id.title_card);
             textFecha=itemView.findViewById(R.id.date_card);
             textComentario=itemView.findViewById(R.id.comment_card);
             deleteNote=itemView.findViewById(R.id.delete_note_button);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                textComentario.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
            }

        }
    }

}
