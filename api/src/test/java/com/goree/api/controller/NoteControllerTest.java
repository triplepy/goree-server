package com.goree.api.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.goree.api.Application;
import com.goree.api.auth.FacebookSettings;
import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.domain.Note;
import com.goree.api.service.NoteService;
import com.goree.api.util.HttpHeaderConstants;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class NoteControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private NoteController noteController;

    @Mock
    private NoteService noteService;

    @Autowired
    private FacebookSettings settings;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(noteController).build();
    }

    @Test
    public void findNoteById() throws Exception {
        // given
        Note expected = new Note();
        expected.setId(1L);
        expected.setContent("content");
        expected.setCreateDate(new Date());
        Group group = new Group();
        group.setId(11L);
        expected.setGroup(group);
        Member noteWriter = new Member();
        noteWriter.setId(111L);
        expected.setNoteWriter(noteWriter);

        when(noteService.findNoteById(expected.getId())).thenReturn(expected);
        
        // when then
        mockMvc.perform(
                get(NoteController.FIND_NOTE_BY_ID_URL, expected.getId())
                        .header(HttpHeaderConstants.AUTH_TOKEN, settings.longLivedTokenForTest()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)expected.getId())))
                .andExpect(jsonPath("$.content", is(expected.getContent())))
                .andExpect(jsonPath("$.createDate", is(expected.getCreateDate().getTime())))
                .andExpect(jsonPath("$.group.id", is((int)expected.getGroup().getId())))
                .andExpect(jsonPath("$.noteWriter.id", is((int)expected.getNoteWriter().getId())));
    }

    @Test
    public void writeNote() throws Exception {
        // given
        Note newNote = new Note();
        newNote.setContent("content");
        Member noteWriter = new Member();
        noteWriter.setId(11L);
        newNote.setNoteWriter(noteWriter);
        Group group = new Group();
        group.setId(21L);
        newNote.setGroup(group);

        Note expected = new Note();
        PropertyUtils.copyProperties(expected, newNote);
        expected.setId(1L);
        expected.setCreateDate(new Date());

        when(noteService.writeNote(newNote)).thenReturn(expected);

        // when then
        String newNoteJson = new ObjectMapper().writeValueAsString(newNote);
        mockMvc.perform(
                post(NoteController.WRITE_NOTE_URL)
                        .header(HttpHeaderConstants.AUTH_TOKEN, settings.longLivedTokenForTest())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newNoteJson))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) expected.getId())))
                .andExpect(jsonPath("$.content", is(expected.getContent())))
                .andExpect(jsonPath("$.createDate", is(expected.getCreateDate().getTime())))
                .andExpect(jsonPath("$.noteWriter.id", is((int)expected.getNoteWriter().getId())))
                .andExpect(jsonPath("$.group.id", is((int)expected.getGroup().getId())));
    }
}
