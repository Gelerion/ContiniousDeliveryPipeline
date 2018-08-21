package com.gelerion.cd.notepad.notepad.domain.service;

import com.gelerion.cd.notepad.notepad.domain.model.Note;

import java.util.List;

/**
 * Created by denis.shuvalov on 02/08/2018.
 */
public interface NoteService {

    Note create(Note note);

    void delete(Note note);

    List<Note> findAll();
}