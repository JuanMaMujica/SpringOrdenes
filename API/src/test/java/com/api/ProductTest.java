package com.api;

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
import com.api.model.Client;
import com.api.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductTest {

	
	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	    
	 @Before
	 public void setup() {
	      mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	 }
	 
	    @Test
	    public void createProduct() throws Exception {
	        String raw = "{\"id\":\"1\\\"\\\"  N\",\"dni\":430000.0}";	//aca va el json armado a mano
	        String jsonResponse = mockMvc
	                .perform(post("/product/").content(raw).contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	        Product product = new ObjectMapper().readValue(jsonResponse, Product.class);
	        assertNotNull(product);
	        System.out.println("Exito en createProduct con los datos: \n " + JsonPath.parse(jsonResponse).json());
	    }
	    
	
	    @Test
	    public void getProducts() throws Exception {
	        String jsonResponse = mockMvc
	                .perform(get("/product/").contentType(MediaType.APPLICATION_JSON)
	                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
	                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
	        Product[] products = new ObjectMapper().readValue(jsonResponse, Product[].class);
	        assertNotNull(products);
	        
	        System.out.println(
	                "Exito en getProducts con los datos: " + products.length + "\n " + JsonPath.parse(jsonResponse).json());
	    }
	    
}
