package com.mycompany.myapp.controller;

import com.mycompany.myapp.SpringSecurityWebAuxTestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = SpringSecurityWebAuxTestConfig.class
)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("admin")
    public void verifiesUsersShowPageAndAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/show"))
               .andExpect(MockMvcResultMatchers.model().hasNoErrors())
               .andExpect(MockMvcResultMatchers.model().attributeExists("users"))
               .andExpect(MockMvcResultMatchers.view().name("users/show"))
               .andExpect(MockMvcResultMatchers.status().isOk());
    }
}