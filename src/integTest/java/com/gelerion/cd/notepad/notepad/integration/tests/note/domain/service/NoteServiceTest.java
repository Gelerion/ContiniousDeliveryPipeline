package com.gelerion.cd.notepad.notepad.integration.tests.note.domain.service;

import com.gelerion.cd.notepad.notepad.domain.model.Note;
import com.gelerion.cd.notepad.notepad.domain.service.NoteService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by denis.shuvalov on 05/08/2018.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class NoteServiceTest {

    @Autowired
    private NoteService noteService;

    private Note note;

    @Before
    public void setUp() {
        note = new Note("Kubernetes", "Best container orchestration tool ever");
    }

    @After
    public void destroy() {
        noteService.delete(note);
    }

    @Test
    public void shouldCreateNoteWithTitleAndContent() {
        Note createdNote = noteService.create(note);
        assertThat(createdNote.getId(), notNullValue());
        assertThat(createdNote.getWordCount(), is(5));
    }

}
