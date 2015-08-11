package com.goree.api.controller;

import com.goree.api.Application;
import com.goree.api.auth.FacebookSettings;
import com.goree.api.domain.Member;
import com.goree.api.domain.Note;
import com.goree.api.domain.NoteComment;
import com.goree.api.service.NoteCommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class NoteCommentRestTest {
    @Autowired
    private FacebookSettings settings;

    private MockMvc mockMvc;

    @Mock
    private NoteCommentService noteCommentService;

    @InjectMocks
    private NoteCommentController controller;

    private Note note;
    private NoteComment noteComment1;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


        note = new Note();
        note.setId(11L);
        noteComment1 = new NoteComment();
        noteComment1.setId(1L);
        noteComment1.setContent("test comment content");
        noteComment1.setCreateDate(new Date());
        noteComment1.setNote(note);
        Member writer = new Member();
        writer.setId(111L);
        writer.setEmail("rpxhdnjsdud@nate.com");
        noteComment1.setWriter(writer);
    }
    @Test
    public void findNoteCommentById() throws Exception {
        // given
        when(noteCommentService.findNoteCommentById(noteComment1.getId()))
                .thenReturn(noteComment1);

        // when then
        mockMvc.perform(
                get("/group/note/comment/"+noteComment1.getId())
                .header("AuthToken", settings.longLivedTokenForTest()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) noteComment1.getId())))
                .andExpect(jsonPath("$.content", is(noteComment1.getContent())))
                .andExpect(jsonPath("$.createDate", is(noteComment1.getCreateDate().getTime())))
                .andExpect(jsonPath("$.note.id", is((int) noteComment1.getNote().getId())))
                .andExpect(jsonPath("$.writer.id", is((int) noteComment1.getWriter().getId())))
                .andExpect(jsonPath("$.writer.email", is(noteComment1.getWriter().getEmail()))
                );
    }

    @Test
    public void findNoteCommentsById() throws Exception {
        NoteComment noteComment2;
        noteComment2 = new NoteComment();
        noteComment2.setId(1L);
        noteComment2.setContent("test comment content");
        noteComment2.setCreateDate(new Date());
        noteComment2.setNote(note);
        Member writer = new Member();
        writer.setId(112L);
        writer.setEmail("getogrand@paran.com");
        noteComment2.setWriter(writer);

        // given
        List<NoteComment> noteComments = Arrays.asList(noteComment1, noteComment2);
        when(noteCommentService.findNoteCommentsByNoteId(note.getId()))
                .thenReturn(noteComments);

        // when then
        mockMvc.perform(
                get("/group/note/comment/s/note/"+note.getId())
                .header("AuthToken", settings.longLivedTokenForTest()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].id", is((int) noteComment1.getId())))
                .andExpect(jsonPath("$.[0].content", is(noteComment1.getContent())))
                .andExpect(jsonPath("$.[0].createDate", is(noteComment1.getCreateDate().getTime())))
                .andExpect(jsonPath("$.[0].note.id", is((int) noteComment1.getNote().getId())))
                .andExpect(jsonPath("$.[0].writer.id", is((int) noteComment1.getWriter().getId())))
                .andExpect(jsonPath("$.[0].writer.email", is(noteComment1.getWriter().getEmail())))
                .andExpect(jsonPath("$.[1].id", is((int) noteComment2.getId())))
                .andExpect(jsonPath("$.[1].content", is(noteComment2.getContent())))
                .andExpect(jsonPath("$.[1].createDate", is(noteComment2.getCreateDate().getTime())))
                .andExpect(jsonPath("$.[1].note.id", is((int) noteComment2.getNote().getId())))
                .andExpect(jsonPath("$.[1].writer.id", is((int) noteComment2.getWriter().getId())))
                .andExpect(jsonPath("$.[1].writer.email", is(noteComment2.getWriter().getEmail())))
                .andExpect(jsonPath("$", hasSize(noteComments.size()))
                );
    }
}
