package com.goree.api.controller;


import com.google.gson.Gson;
import com.goree.api.auth.FacebookSettings;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


public class GroupRestTest extends RestTestWithDBUnit{

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private FacebookSettings settings;

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/group_test_setup.xml";
    }


    @Test
    public void makingGroup () throws Exception {
        Member leader = memberMapper.selectMemberByEmail("arst@arst.com");

        Group expected = new Group();
        expected.setName("It is Group" + new Date().getTime());
        expected.setDescription("It is description");
        expected.setLeader(leader);

        String jsonData = new Gson().toJson(expected);

        performSet(post("/group"),jsonData)
            .andExpect(jsonPath("$.name").value(expected.getName()))
            .andExpect(jsonPath("$.description").value(expected.getDescription()))
            .andExpect(jsonPath("$.leader.email").value(expected.getLeader().getEmail()));

    }



    @Test
    public void updateGroupImage() throws Exception{
        File file = new File("src/test/resources/static/Image_upload_test.jpg");
        FileInputStream fis = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(),null,fis);

        performSet(fileUpload("/group/id/1/updateImage").file(multipartFile))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.imagePath").exists());



    }


}
