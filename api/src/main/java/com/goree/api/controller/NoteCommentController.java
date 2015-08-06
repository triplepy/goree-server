package com.goree.api.controller;

import com.goree.api.domain.NoteComment;
import com.goree.api.service.NoteCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/group/note/comment")
public class NoteCommentController {
    @Autowired
    private NoteCommentService noteCommentService;


    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public NoteComment findNoteCommentById(long id) {
        return noteCommentService.findNoteCommentById(id);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public List<NoteComment> findNoteCommentsById(long noteId) {
        return noteCommentService.findNoteCommentsById(noteId);
    }

    /**
     * @api
     * @apiGroup
     * @apiDescription
     */
    public NoteComment writeNoteComment(NoteComment noteComment) {
        return noteCommentService.writeNoteComment(noteComment);
    }
}
