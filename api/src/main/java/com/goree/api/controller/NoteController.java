package com.goree.api.controller;


import com.goree.api.domain.Note;
import com.goree.api.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NoteController {
    private static final String URL_PREFIX = "/group/note";
    public static final String FIND_NOTE_BY_ID_URL = URL_PREFIX + "/{id}";
    public static final String WRITE_NOTE_URL = URL_PREFIX;
    @Autowired
    private NoteService noteService;

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value = FIND_NOTE_BY_ID_URL, method = RequestMethod.GET)
    public Note findNoteById(@PathVariable long id) {
        return noteService.findNoteById(id);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value = WRITE_NOTE_URL, method = RequestMethod.POST)
    public Note writeNote(@RequestBody Note note) {
        return noteService.writeNote(note);
    }
}
