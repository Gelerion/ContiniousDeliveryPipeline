package com.gelerion.cd.notepad.notepad.domain.service;

import com.gelerion.cd.notepad.notepad.domain.model.Note;
import com.gelerion.cd.notepad.notepad.domain.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by denis.shuvalov on 02/08/2018.
 */
@Service
public class DefaultNoteService implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Note create(Note note) {
        return noteRepository.save(note);
    }

    @Override
    public void delete(Note note) {
        noteRepository.delete(note);
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAll();
    }
}