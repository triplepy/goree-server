package com.goree.api.mapper;


import com.goree.api.domain.NoteComment;

public interface NoteCommentMapper {
    NoteComment selectNoteCommentById(int id);
}
