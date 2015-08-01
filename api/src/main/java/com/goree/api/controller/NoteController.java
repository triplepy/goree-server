package com.goree.api.controller;


import com.goree.api.domain.Note;
import com.goree.api.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {
    @Autowired
    private NoteService noteService;

    public Note findNoteById(long id) {
        return noteService.findNoteById(id);
    }

    public Note writeNote(Note note) {
        return noteService.writeNote(note);
    }
}
