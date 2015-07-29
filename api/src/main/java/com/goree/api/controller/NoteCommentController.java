package com.goree.api.controller;

import com.goree.api.domain.NoteComment;
import com.goree.api.service.NoteCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NoteCommentController {
    @Autowired
    private NoteCommentService noteCommentService;


    public NoteComment findNoteCommentById(int id) {
        return noteCommentService.findNoteCommentById(id);
    }

    public List<NoteComment> findNoteCommentsById(int noteId) {
        return noteCommentService.findNoteCommentsById(noteId);
    }

    public NoteComment writeNoteComment(NoteComment noteComment) {
        return noteCommentService.writeNoteComment(noteComment);
    }
}
