package com.gtc;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by gtc on 2016/12/23.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:META-INF/infrastructure.xml",
        "classpath:META-INF/applicationContext.xml", "classpath:WEB-INF/spring-dispatcher-servlet.xml"})

public class controllerTest {

    @Autowired
    private WebApplicationContext mockWebContext;

    private MockMvc mockMvc;
    @Before

    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(mockWebContext).build();
    }
    @Test
    public void getUsers() throws Exception {

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUser() throws Exception {
        mockMvc.perform(get("/rest/user/1"))
                .andExpect(status().isOk());
    }

}