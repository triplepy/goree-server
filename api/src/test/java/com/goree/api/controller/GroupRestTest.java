package com.goree.api.controller;


import com.google.gson.Gson;
import com.goree.api.domain.Group;
import com.goree.api.domain.Member;
import com.goree.api.mapper.MemberMapper;
import com.goree.api.util.RestTestWithDBUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class GroupRestTest extends RestTestWithDBUnit{

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/group_test_setup.xml";
    }


    @Test
    public void addGroup() throws Exception {
        Member leader = memberMapper.selectMemberByEmail("arst@arst.com");

        Group expected = new Group();
        expected.setName("It is Group" + new Date().getTime());
        expected.setDescription("It is description");
        expected.setLeader(leader);

        String jsonData = new Gson().toJson(expected);

        System.out.println(jsonData);


        performSet(post(GroupController.ADD_GROUP_URL), jsonData)
            .andExpect(jsonPath("$.name").value(expected.getName()))
            .andExpect(jsonPath("$.description").value(expected.getDescription()))
            .andExpect(jsonPath("$.leader.email").value(expected.getLeader().getEmail()));

    }



    @Test
    public void updateGroupImage() throws Exception{
        File file = new File("src/test/resources/static/Image_upload_test.jpg");
        FileInputStream fis = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(),null,fis);

        performSet(fileUpload(GroupController.UPDATE_IMAGE_URL, 1).file(multipartFile))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.imagePath").exists());
    }

    @Test
    public void findGroupAll() throws Exception {
        performSet(get(GroupController.FIND_GROUP_ALL_URL))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].leader.id").value(1))
                .andExpect(jsonPath("$.[0].name").value("abcbacba"))
                .andExpect(jsonPath("$.[0].description").value("artsarstars"));

    }

    @Test
    public void findGroupsJoined() throws Exception {
        int memberId = 1;

        performSet(get(GroupController.FIND_GROUPS_JOINED_URL, memberId))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void findGroupById() throws Exception {
        int groupId = 1;
        performSet(get(GroupController.FIND_GROUP_BY_ID_URL, groupId))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.leader.id").value(1))
                .andExpect(jsonPath("$.name").value("abcbacba"))
                .andExpect(jsonPath("$.description").value("artsarstars"))
                .andExpect(jsonPath("$.memberCount").value(1));
    }

    @Test
    public void findGroupByName() throws Exception {
        String groupName = "abcbacba";

        performSet(get(GroupController.FIND_GROUP_BY_NAME_URL, groupName))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.leader.id").value(1))
                .andExpect(jsonPath("$.name").value("abcbacba"))
                .andExpect(jsonPath("$.description").value("artsarstars"))
                .andExpect(jsonPath("$.memberCount").value(1));
    }


    @Test
    public void joinMember() throws Exception {
        int groupId = 1;
        int memberId = 2;

        performSet(put(GroupController.JOIN_MEMBER_URL, groupId, memberId));

        int expectedMemberCount = 2;

        performSet(get(GroupController.FIND_GROUP_BY_ID_URL, groupId))
                .andExpect(jsonPath("$.id").value(groupId))
                .andExpect(jsonPath("$.leader.id").value(1))
                .andExpect(jsonPath("$.name").value("abcbacba"))
                .andExpect(jsonPath("$.description").value("artsarstars"))
                .andExpect(jsonPath("$.memberCount").value(expectedMemberCount));

    }




}
