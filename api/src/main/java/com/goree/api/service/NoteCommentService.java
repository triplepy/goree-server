package com.goree.api.service;

import com.goree.api.domain.NoteComment;
import com.goree.api.mapper.NoteCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteCommentService {
    @Autowired
    private NoteCommentMapper noteCommentMapper;

    public NoteComment findNoteCommentById(long id) {
        return noteCommentMapper.selectNoteCommentById(id);
    }

    public List<NoteComment> findNoteCommentsById(long noteId) {
        return noteCommentMapper.selectNoteCommentsByNoteId(noteId);
    }

    public NoteComment writeNoteComment(NoteComment noteComment) {

        noteCommentMapper.insertNoteComment(noteComment);

        return noteCommentMapper.selectNoteCommentLastOneByNoteId(noteComment.getNote().getId());
    }
}
