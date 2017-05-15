package com.greenfoxacademy.controller;

import com.greenfoxacademy.TestsApplication;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestsApplication.class)
@WebAppConfiguration
@EnableWebMvc
public class GuardianControllerTest {
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void testGrootWithString() throws Exception {
    String message = "Hogy vagy?";
    mockMvc.perform(get("/groot")
        .param("message", message))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.received", is(message)))
        .andExpect(jsonPath("$.translated", is("I am Groot!")));
  }

  @Test
  public void testGrootWithNoParameter() throws Exception {
    mockMvc.perform(get("/groot"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.error", is("I am Groot!")));
  }

  @Test
  public void testYonduWithParameters() throws Exception {
    Double distance = 100.0;
    Double time = 10.0;
    mockMvc.perform(get("/yondu")
        .param("distance", distance.toString())
        .param("time", time.toString()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.distance", is(distance)))
        .andExpect(jsonPath("$.time", is(time)))
        .andExpect(jsonPath("$.speed", is(10.0)));
  }

  @Test
  public void testYonduWithMissingParameters() throws Exception {
    mockMvc.perform(get("/yondu"))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.error", is("Please give distance and time parameters")));
  }

  @Test
  public void testYonduWithZeroTime() throws Exception {
    Double distance = 100.0;
    Double time = 0.0;
    mockMvc.perform(get("/yondu")
        .param("distance", distance.toString())
        .param("time", time.toString()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(jsonPath("$.speed", is("Infinity")));
  }
}