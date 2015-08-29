package com.goree.api.controller;


import com.goree.api.Application;
import com.goree.api.auth.FacebookSettings;
import com.goree.api.domain.Place;
import com.goree.api.service.PlaceService;
import com.goree.api.util.HttpHeaderConstants;
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

import java.math.BigDecimal;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class})
@WebAppConfiguration
public class PlaceControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    private PlaceController placeController;

    @Mock
    private PlaceService placeService;

    @Autowired
    private FacebookSettings settings;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(placeController).build();
    }

    @Test
    public void findPlaceById() throws Exception {
        // given
        Place expected = new Place();
        expected.setId(1L);
        expected.setName("goodnews");
        expected.setAddress("서울시 용산구 청파동 선린인고 앞");
        expected.setXCoordinate(new BigDecimal("36.017194"));
        expected.setYCoordinate(new BigDecimal("128.6978236"));

        when(placeService.findPlaceById(expected.getId())).thenReturn(expected);

        // when then
        mockMvc.perform(
                get(PlaceController.FIND_PLACE_BY_ID, expected.getId())
                        .header(HttpHeaderConstants.AUTH_TOKEN, settings.longLivedTokenForTest()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int)expected.getId())))
                .andExpect(jsonPath("$.name", is(expected.getName())))
                .andExpect(jsonPath("$.address", is(expected.getAddress())))
                .andExpect(jsonPath("$.xCoordinate", is(expected.getXCoordinate().doubleValue())))
                .andExpect(jsonPath("$.yCoordinate", is(expected.getYCoordinate().doubleValue())));
    }
}
