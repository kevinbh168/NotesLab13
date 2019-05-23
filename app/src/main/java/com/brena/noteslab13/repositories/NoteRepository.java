package com.brena.noteslab13.repositories;

import com.brena.noteslab13.activities.MainActivity;
import com.brena.noteslab13.models.Note;
import com.brena.noteslab13.models.User;
import com.orm.SugarRecord;


import java.util.List;

public class NoteRepository {

    public static void create_note(Long usuario_id, String titulo, String comentario, String fecha ){
        Note note=new Note();
        note.setUsuario_id(usuario_id);
        note.setTitle(titulo);
        note.setContent(comentario);
        note.setDate(fecha);
        SugarRecord.save(note);
    }

    public static List<Note> getAllNotes(Long id){
        List<Note> notes=SugarRecord.find(Note.class,"usuarioid=?",String.valueOf(id));
        return notes;
    }

    public static void deleteNote(Long id){
        Note note=SugarRecord.findById(Note.class,id);
        SugarRecord.delete(note);

        MainActivity mn=new MainActivity();

    }
}
