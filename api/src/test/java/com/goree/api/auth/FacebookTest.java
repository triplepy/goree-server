package com.goree.api.auth;

import com.goree.api.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes={Application.class})
@WebAppConfiguration
public class FacebookTest {
    @Autowired
    private WebApplicationContext webContext;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();

        if (TokenContext.longLivedToken() == null) {
            String shortLivedToken = "CAAMeZCXQ4irsBACWkld16WmFjGZAaxV6ocvBiel1YuWSbcfdMfOTceflKRLbMvj0Jv5I3xuq9JouaqAlyqrEkgZCPRchf4HHEtdSDkdZCV4XRPOoKiwE5ZCFihqxbti2DNe7rt9iizTXZC1ZAbCOhN3VBMrTtPdA9nAfk9kSKLjxZCKj7zcy7heYwr0TLyfnlBcqPYrlMX3NLBHT5m1h1IKI9WSHeTjpjnEZD";
            this.mockMvc.perform(get("/short-lived-token/" + shortLivedToken))
                    .andExpect(status().isOk())
                    .andDo(print())
                    .andExpect(content().string(is(not(""))));
        }
    }

    @Test
    public void transformShortLivedTokenToLongLivedTokenAndStore() throws Exception {
        Assert.assertTrue(TokenContext.longLivedToken().length() > 100);
    }

    @Test
    public void retrieveUserProfile() throws Exception {
        this.mockMvc.perform(get("/user_profile"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value("Susan Amiedcjeciii Putnamwitz"));
    }
}
