package com.gelerion.cd.notepad.notepad.domain.repository;

import com.gelerion.cd.notepad.notepad.domain.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by denis.shuvalov on 02/08/2018.
 */
public interface NoteRepository extends JpaRepository<Note, String> {

}