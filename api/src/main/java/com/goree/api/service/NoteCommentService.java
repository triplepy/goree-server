package com.goree.api.service;

import com.goree.api.domain.NoteComment;
import com.goree.api.mapper.NoteCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteCommentService {
    @Autowired
    private NoteCommentMapper noteCommentMapper;

    public NoteComment findNoteCommentById(int id) {
        return noteCommentMapper.selectNoteCommentById(id);
    }

}
