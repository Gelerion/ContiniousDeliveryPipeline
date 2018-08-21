package com.gelerion.cd.notepad.notepad.api;

import com.gelerion.cd.notepad.notepad.domain.model.Note;
import com.gelerion.cd.notepad.notepad.domain.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Created by denis.shuvalov on 02/08/2018.
 */
@Controller
public class NoteController {

    @Autowired
    private NoteService noteService;

    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("index");
        List<Note> notes = noteService.findAll();
        modelAndView.addObject("notes", notes);
        return modelAndView;
    }

    @PostMapping("/notes")
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public Note create(@RequestBody @Valid Note note) {
        LOG.debug("Create note request: {}", note);
        return noteService.create(note);
    }
}
