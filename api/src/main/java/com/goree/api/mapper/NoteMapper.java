package com.goree.api.mapper;

import com.goree.api.domain.Note;

public interface NoteMapper {

    Note selectNoteById(long id);

    void insertNote(Note note);

    Note selectLastNoteByGroupId(long groupId);
}
