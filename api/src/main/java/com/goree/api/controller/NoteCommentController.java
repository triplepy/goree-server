package com.goree.api.controller;

import com.goree.api.domain.NoteComment;
import com.goree.api.service.NoteCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/group/note/comment")
public class NoteCommentController {
    @Autowired
    private NoteCommentService noteCommentService;


    /**
     * //TODO doc 작성
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public NoteComment findNoteCommentById(@PathVariable long id) {
        return noteCommentService.findNoteCommentById(id);
    }

    /**
     * //TODO doc 작성
     * @api
     * @apiGroup
     * @apiDescription
     */
    @RequestMapping(value = "/s/note/{noteId}")
    public List<NoteComment> findNoteCommentsByNoteId(@PathVariable long noteId) {
        return noteCommentService.findNoteCommentsByNoteId(noteId);
    }

    /**
     * //TODO doc 작성
     * @api
     * @apiGroup
     * @apiDescription
     */
    public NoteComment writeNoteComment(NoteComment noteComment) {
        return noteCommentService.writeNoteComment(noteComment);
    }
}
