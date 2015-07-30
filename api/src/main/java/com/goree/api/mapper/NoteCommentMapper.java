package com.goree.api.mapper;


import com.goree.api.domain.NoteComment;

import java.util.List;

public interface NoteCommentMapper {
    NoteComment selectNoteCommentById(long id);

    List<NoteComment> selectNoteCommentsByNoteId(long noteId);

    void insertNoteComment(NoteComment noteComment);

    NoteComment selectNoteCommentLastOneByNoteId(long noteId);
}
