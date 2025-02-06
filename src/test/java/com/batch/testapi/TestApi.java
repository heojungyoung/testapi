package com.batch.testapi;

import com.batch.testapi.controller.GetController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GetController.class)
class TestApi {

    // -Dspring.profiles.active=test

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetHello_withVersion() throws Exception {
        // Mock request with "version" parameter
        mockMvc.perform(get("/main/v1/check")
                        .param("version", "1.0"))
                .andExpect(status().isOk())
                .andExpect(content().string("check version"));
    }


    @Test
    void testSort() throws Exception {
        // Mock request with "version" parameter
        mockMvc.perform(get("/main/v1/sortTest"))
                .andExpect(status().isOk());
    }

    @Test
    void testKebin() throws Exception {
        // Mock request with "version" parameter
        mockMvc.perform(get("/main/v1/kebin"))
                .andExpect(status().isOk());
    }



}




