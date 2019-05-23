package com.brena.noteslab13.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.brena.noteslab13.R;
import com.brena.noteslab13.adapters.CommentsAdapter;
import com.brena.noteslab13.models.Note;
import com.brena.noteslab13.repositories.NoteRepository;
import com.brena.noteslab13.repositories.UserRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private EditText titleHome;
    private FloatingActionButton add_note;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView_comment);
        titleHome=findViewById(R.id.title_home);
        add_note=findViewById(R.id.add_note_button);

        SharedPreferences sp= PreferenceManager.getDefaultSharedPreferences(this);
        titleHome.setText("Bienvenido "+sp.getString("nombre","No tiene nombre"));
        Long usuario_id=sp.getLong("id",0);
        initRecycleView(usuario_id);

            add_note.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showRegisterNote();
                }
            });

    }

    private void showRegisterNote(){
        startActivity(new Intent(this,RegisterNoteActivity.class));
    }


    public void initRecycleView(Long usuario_id){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CommentsAdapter adapter = new CommentsAdapter();
        final List<Note> notes = NoteRepository.getAllNotes(usuario_id);
        adapter.setComments(notes);
        recyclerView.setAdapter(adapter);
    }

    private void initToolbar() {
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notas");

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.cerrar_sesion) {
            UserRepository.callLogout(this);
            startActivity(new Intent(this,LoginActivity.class));
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }



}
