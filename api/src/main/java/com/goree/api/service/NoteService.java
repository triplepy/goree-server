package com.goree.api.service;

import com.goree.api.domain.Note;
import com.goree.api.mapper.NoteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    @Autowired
    private NoteMapper noteMapper;


    public Note findNoteById(int id) {
        return noteMapper.selectNoteById(id);
    }

    public Note writeNote(Note note) {
        noteMapper.insertNote(note);
        return noteMapper.selectLastNoteByGroupId(note.getGroup().getId());
    }
}
