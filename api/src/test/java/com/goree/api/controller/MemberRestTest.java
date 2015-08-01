package com.goree.api.controller;


import com.google.gson.Gson;
import com.goree.api.domain.Member;
import com.goree.api.domain.Tag;
import com.goree.api.util.TestWithDBUnit;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebIntegrationTest({"server.port=28080"})
public class MemberRestTest extends TestWithDBUnit {

    @Autowired
    private WebApplicationContext wac;


    private MockMvc mockMvc;

    @Before
    public void setUp() {
        super.setUp();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Override
    public String getDatasetFilePath() {
        return "src/test/resources/testdataset/member_test_setup.xml";
    }


    public ResultActions performSet(MockHttpServletRequestBuilder requestBuilder) throws Exception{
            return this.mockMvc.perform(requestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                    .andDo(print())
                    .andExpect(status().isOk());


    }

    public ResultActions performSet(MockHttpServletRequestBuilder requestBuilder,String jsonData) throws Exception{
        return this.mockMvc.perform(requestBuilder
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")).content(jsonData)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print())
                .andExpect(status().isOk());

    }
    @Test
    public void findMemberAll() throws Exception {
        performSet(get("/member"))
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$", hasSize(2)))
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
        performSet(delete("/member/id/1"));

        performSet(get("/member"))
                .andExpect(jsonPath("$",hasSize(1)));

    }

    @Test
    public void registerMember() throws Exception {
        Member expected = new Member();
        expected.setEmail("rpxhdnjsdud"+new Date().getTime()+"@nate.com");
        expected.setPassword("qlalfqjsgh");
        expected.setFullName("Wonyoung Ju");
        expected.setAge(22);
        expected.setNickname("nickname");
        expected.setGender(Member.Gender.M);
        expected.setPhone("010-8826-0173");
        expected.setJob("programmer");
        Tag tag = new Tag();
        tag.setName("memberTest");
        expected.setTags(Arrays.asList(tag));

        String json = new Gson().toJson(expected);


        performSet(post("/member"),json)
                .andExpect(jsonPath("$.email").value(expected.getEmail()))
                .andExpect(jsonPath("$.fullName").value(expected.getFullName()))
                .andExpect(jsonPath("$.age").value(expected.getAge()))
                .andExpect(jsonPath("$.nickname").value(expected.getNickname()))
                .andExpect(jsonPath("$.gender").value(expected.getGender().name()))
                .andExpect(jsonPath("$.phone").value(expected.getPhone()))
                .andExpect(jsonPath("$.job").value(expected.getJob()));

    }

}