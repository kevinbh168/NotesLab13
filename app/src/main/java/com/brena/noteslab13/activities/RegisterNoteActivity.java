package com.brena.noteslab13.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.brena.noteslab13.R;
import com.brena.noteslab13.repositories.NoteRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class RegisterNoteActivity extends AppCompatActivity {

    private EditText titleComment;
    private EditText contentComment;
    private Button registerComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_note);

        titleComment = findViewById(R.id.titulo_register);
        contentComment = findViewById(R.id.nota_register);
        registerComment = findViewById(R.id.register_comment);

        registerComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    showCreateNote();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void showCreateNote() throws ParseException {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        Long usuario_id = sp.getLong("id", 0);
        String title = String.valueOf(titleComment.getText());
        String comment = String.valueOf(contentComment.getText().toString());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        String datetime = dateformat.format(c.getTime());
        Log.d("ed", datetime);
        if (title.isEmpty() || comment.isEmpty()) {
            Toast.makeText(this, "Completar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        try {

            NoteRepository.create_note(usuario_id, title, comment, datetime);
            Toast.makeText(this, "Registro de satisfactorio", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
