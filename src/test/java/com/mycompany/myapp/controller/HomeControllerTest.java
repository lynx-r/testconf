package com.mycompany.myapp.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void verifiesHomePageLoads() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
               .andExpect(MockMvcResultMatchers.model().hasNoErrors())
               .andExpect(MockMvcResultMatchers.model().attributeExists("schedule"))
               .andExpect(MockMvcResultMatchers.view().name("index"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }
}