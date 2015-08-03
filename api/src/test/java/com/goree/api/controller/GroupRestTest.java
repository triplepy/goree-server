package com.goree.api.controller;


import com.goree.api.auth.FacebookSettings;
import com.goree.api.mapper.MemberMapper;
import com.goree.api.util.RestTestWithDBUnit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


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
    public void updateGroupImage() throws Exception{
        File file = new File("src/test/resources/static/Image_upload_test.jpg");
        FileInputStream fis = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(),null,fis);

        mockMvc
                .perform(fileUpload("/group/id/1/updateImage").file(multipartFile)
                                .header("AuthToken", settings.longLivedTokenForTest()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.imagePath").exists());



    }


}
