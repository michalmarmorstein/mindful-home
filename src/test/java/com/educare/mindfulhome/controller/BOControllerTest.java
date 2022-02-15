//package com.educare.mindfulhome.controller;
//
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import com.educare.mindfulhome.controller.payload.CreateActivityRequest;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//@WebMvcTest(controllers = BOController.class)
//class BOControllerTest {
//
//  @Autowired
//  private MockMvc mockMvc;
//  @Autowired
//  private ObjectMapper objectMapper;
//
//  @Test
//  void whenEmptyName_thenReturns400() throws Exception {
//    CreateActivityRequest createActivityRequest = new CreateActivityRequest("", "www");
//
//    mockMvc.perform(post("/bo/activity")
//            .contentType("application/json")
//            .content(objectMapper.writeValueAsString(createActivityRequest)))
//        .andExpect(status().isBadRequest());
//  }
//
//}
