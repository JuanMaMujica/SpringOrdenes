package com.api;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.notNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.api.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTest  {


    @Autowired
    private WebApplicationContext wac;
  
    private MockMvc mockMvc;
    
    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void createClient() throws Exception {
        String raw = "{\"id\":\"1\\\"\\\"  N\",\"dni\":430000.0}";	//aca va el json armado a mano
        String jsonResponse = mockMvc
                .perform(post("/product/").content(raw).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        Client client = new ObjectMapper().readValue(jsonResponse, Client.class);
        assertNotNull(client);
        assertFalse(client.getName().isEmpty()); 
        System.out.println("Exito en createProduct con los datos: \n " + JsonPath.parse(jsonResponse).json());
    }

    @Test
    public void deleteUser() throws Exception {
        String jsonResponse = mockMvc
                .perform(delete("/user/1").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println("Exito en deleteClient con salida: \n " + jsonResponse);
        assertTrue(jsonResponse.equals("true"));
    }
}
