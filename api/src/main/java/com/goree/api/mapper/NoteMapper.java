package com.goree.api.mapper;

import com.goree.api.domain.Note;

public interface NoteMapper {

    Note selectNoteById(int id);
}
