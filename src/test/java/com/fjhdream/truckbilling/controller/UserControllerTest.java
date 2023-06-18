package com.fjhdream.truckbilling.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fjhdream.truckbilling.TestContainerConfiguration;
import com.fjhdream.truckbilling.controller.entity.request.UserRequest;
import com.fjhdream.truckbilling.service.wx.WxService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@Import(TestContainerConfiguration.class)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WxService wxService;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void create_user_and_succeed() throws Exception {
        this.mockMvc.perform(post("/user")
                .content(asJsonString(new UserRequest("test_user_id", "test_user_name", "test_avatar_url")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }
}