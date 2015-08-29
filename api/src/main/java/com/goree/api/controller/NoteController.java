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
     * @api {get} /group/note/:id Find note by id
     * @apiGroup Note
     * @apiDescription note id를 통해 note 한 개를 받아온다.
     * @apiParam {long} noteId
     */
    @RequestMapping(value = FIND_NOTE_BY_ID_URL, method = RequestMethod.GET)
    public Note findNoteById(@PathVariable long id) {
        return noteService.findNoteById(id);
    }

    /**
     * @api {post} /group/note Write note
     * @apiGroup Note
     * @apiDescription note 한 개를 생성한다. body에 json 형태로 note 객체 정보를 전달하면 된다.
     */
    @RequestMapping(value = WRITE_NOTE_URL, method = RequestMethod.POST)
    public Note writeNote(@RequestBody Note note) {
        return noteService.writeNote(note);
    }
}
