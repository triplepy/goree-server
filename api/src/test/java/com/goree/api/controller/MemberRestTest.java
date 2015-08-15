package com.goree.api.controller;


import com.google.gson.Gson;
import com.goree.api.domain.Member;
import com.goree.api.domain.Tag;
import com.goree.api.util.RestTestWithDBUnit;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class MemberRestTest extends RestTestWithDBUnit {

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/member_test_setup.xml";
    }


    @Test
    public void findMemberAll() throws Exception {
        performSet(get("/member"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].email").value("arst@arst.com"))
                .andExpect(jsonPath("$[0].fullName").value("arstarstar"))
                .andExpect(jsonPath("$[0].nickname").value("arstarst"))
                .andExpect(jsonPath("$[0].age").value(20))
                .andExpect(jsonPath("$[0].gender").value("M"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].email").value("asdf@arst.com"))
                .andExpect(jsonPath("$[1].fullName").value("arstarstar"))
                .andExpect(jsonPath("$[1].nickname").value("asdf"))
                .andExpect(jsonPath("$[1].age").value(20))
                .andExpect(jsonPath("$[1].gender").value("M"));
    }

    @Test
    public void findMemberByid() throws Exception {
        performSet(get("/member/id/1"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("arst@arst.com"))
                .andExpect(jsonPath("$.fullName").value("arstarstar"))
                .andExpect(jsonPath("$.nickname").value("arstarst"))
                .andExpect(jsonPath("$.age").value(20))
                .andExpect(jsonPath("$.gender").value("M"));


    }

    @Test
    public void deleteMemberById() throws Exception{
        performSet(delete("/member/id/3"));

        performSet(get("/member"))
                .andExpect(jsonPath("$",hasSize(2)));

    }

    @Test
    public void registerMember() throws Exception {
        Member expected = new Member();
        expected.setEmail("arstarst@nate.com");
        expected.setPassword("qlalfqjsgh");
        expected.setFullName("Wonyoung Ju");
        expected.setAge(22);
        expected.setNickname("nickname");
        expected.setGender(Member.Gender.M);
        expected.setPhone("010-1234-0173");
        expected.setJob("programmer");
        expected.setFacebookUserId("ARrseitnaris");

        Tag tag = new Tag();
        tag.setName("memberTest");
        expected.setTags(Arrays.asList(tag));


        String json = new Gson().toJson(expected);

        mockMvc.perform(post("/member/join")
                .content(json)
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email").value(expected.getEmail()))
                    .andExpect(jsonPath("$.fullName").value(expected.getFullName()))
                    .andExpect(jsonPath("$.age").value(expected.getAge()))
                    .andExpect(jsonPath("$.nickname").value(expected.getNickname()))
                    .andExpect(jsonPath("$.gender").value(expected.getGender().name()))
                    .andExpect(jsonPath("$.phone").value(expected.getPhone()))
                    .andExpect(jsonPath("$.job").value(expected.getJob()))
                    .andExpect(jsonPath("$.facebookUserId", is(expected.getFacebookUserId())));

    }

    @Test
    public void updateMemberImage() throws Exception {
        long memberId = 1L;

        File file = new File("src/test/resources/static/Image_upload_test.jpg");
        FileInputStream fis = new FileInputStream(file);
        MockMultipartFile multipartFile = new MockMultipartFile("file", file.getName(),null,fis);


        performSet(fileUpload("/member/id/"+ memberId + "/updateImage").file(multipartFile))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.imagePath").value(notNullValue()));


    }

}
