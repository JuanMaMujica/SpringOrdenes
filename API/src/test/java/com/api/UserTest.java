package com.api;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.api.model.Bill;
import com.api.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	    
	 @Before
	 public void setup() {
	      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	 }
	 
	    @Test
	    public void createUser() throws Exception {
	        String raw = "{\"id\":\"1\\\"\\\"  N\",\"dni\":430000.0}";	//aca va el json armado a mano
	        String jsonResponse = mockMvc
	                .perform(post("/user/").content(raw).contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	        User user = new ObjectMapper().readValue(jsonResponse, User.class);
	        assertNotNull(user);
	        System.out.println("Exito en createProduct con los datos: \n " + JsonPath.parse(jsonResponse).json());
	    }
	    
	    @Test
	    public void updateUser() throws Exception {
	        String raw = "{\"productId\":1,\"name\":\"PC LENOVO Yoga 3 8\\\"\\\"  N\",\"price\":430000.0}";
	        String jsonResponse = mockMvc
	                .perform(put("/user/").content(raw).contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	        User user = new ObjectMapper().readValue(jsonResponse, User.class);
	        assertNotNull(user);
	        System.out.println("Exito en updateProduct con los datos: \n " + JsonPath.parse(jsonResponse).json());
	    }
}
