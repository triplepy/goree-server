package com.goree.api.mapper;


import com.goree.api.domain.NoteComment;

import java.util.List;

public interface NoteCommentMapper {
    NoteComment selectNoteCommentById(int id);

    List<NoteComment> selectNoteCommentsByNoteId(int noteId);

    void insertNoteComment(NoteComment noteComment);

    NoteComment selectNoteCommentLastOneByNoteId(int noteId);
}
