package com.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.api.model.BillDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BillTest {
	 
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	    
	 @Before
	 public void setup() {
	      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	 }
	 
	    @Test
	    public void createBill() throws Exception {
	        String raw = "{\"id\":\"1\\\"\\\"  N\",\"dni\":430000.0}";	//aca va el json armado a mano
	        String jsonResponse = mockMvc
	                .perform(post("/bill/").content(raw).contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	        Bill bill = new ObjectMapper().readValue(jsonResponse, Bill.class);
	        assertNotNull(bill);
	        System.out.println("Exito en createProduct con los datos: \n " + JsonPath.parse(jsonResponse).json());
	    }
	    
	    @Test
	    public void getBills() throws Exception {
	        String jsonResponse = mockMvc
	                .perform(get("/bill/billDetails"))
	                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	        BillDetails[] bills = new ObjectMapper().readValue(jsonResponse, BillDetails[].class);
	        assertNotNull(bills);
	        
	        System.out.println(
	                "Exito en getbills con los datos: " + bills.length + "\n " + JsonPath.parse(jsonResponse).json());
	    }
	    
	  
}
