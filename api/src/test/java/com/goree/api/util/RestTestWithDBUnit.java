package com.goree.api.util;


import com.goree.api.auth.FacebookSettings;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebIntegrationTest({"server.port=48181"})
abstract public class RestTestWithDBUnit extends TestWithDBUnit {
    @Override
    abstract public String getDatasetFilePath();

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FacebookSettings settings;

    protected MockMvc mockMvc;

    @Before
    public void setUp() {
        super.setUp();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    public ResultActions performSet(MockHttpServletRequestBuilder requestBuilder) throws Exception{
        return this.mockMvc.perform(requestBuilder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"))
                .header(HttpHeaderConstants.AUTH_TOKEN, settings.longLivedTokenForTest()))
                .andDo(print())
                .andExpect(status().isOk());


    }

    public ResultActions performSet(MockHttpServletRequestBuilder requestBuilder,String jsonData) throws Exception{
        return this.mockMvc.perform(requestBuilder
                .header(HttpHeaderConstants.AUTH_TOKEN, settings.longLivedTokenForTest())
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")).content(jsonData)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print())
                .andExpect(status().isOk());

    }

    public ResultActions performSet(MockHttpServletRequestBuilder requestBuilder,byte[] contentData) throws Exception{
        return this.mockMvc.perform(requestBuilder
                .header(HttpHeaderConstants.AUTH_TOKEN, settings.longLivedTokenForTest())
                .contentType(MediaType.parseMediaType("application/json;charset=UTF-8")).content(contentData)
                .accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andDo(print())
                .andExpect(status().isOk());

    }

}
