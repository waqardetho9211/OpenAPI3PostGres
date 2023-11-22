package com.exampleHotel.demo.web.controller;

import com.exampleHotel.demo.data.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = Replace.ANY)
class CustomerControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllCustomers() throws Exception {
        this.mockMvc.perform(get("/customers")).andExpect(status().isOk())
                .andExpect(content().string(containsString("054b145c-ddbc-4136-a2bd-7bf45ed1bef7")))
                .andExpect(content().string(containsString("c04ca077-8c40-4437-b77a-41f510f3f185")));
    }

    @Test
    void getCustomer() throws Exception {
        this.mockMvc.perform(get("/customers/054b145c-ddbc-4136-a2bd-7bf45ed1bef7")).andExpect(status().isOk())
                .andExpect(content().string(containsString("054b145c-ddbc-4136-a2bd-7bf45ed1bef7")));
    }

    @Test
    void getCustomer_notFound() throws Exception {
        this.mockMvc.perform(get("/customers/054b145c-ddbc-4146-a1bd-6bf34ed1bef6")).andExpect(status().isNotFound());
    }

    @Test
    void addCustomer() throws Exception {
        Customer customer = new Customer("", "John", "Doe", "jdoe@test.com", "555-515-1234", "1234 Main Street; Anytown, KS 66110");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(customer);
        this.mockMvc.perform(post("/customers").content(jsonString).contentType("application/json")).andExpect(status().isCreated())
                .andExpect(content().string(containsString("jdoe@test.com")));
    }

    @Test
    void addCustomer_exists() throws Exception {
        Customer customer = new Customer("", "Sydney", "'Bartlett'", "nibh@ultricesposuere.edu", "555-515-1234", "1234 Main Street; Anytown, KS 66110");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(customer);
        this.mockMvc.perform(post("/customers").content(jsonString).contentType("application/json")).andExpect(status().isConflict());

    }

    @Test
    void updateCustomer() throws Exception {
        Customer customer = new Customer("c04ca077-8c40-4437-b77a-41f510f3f185", "John", "Doe", "quam.quis.diam@facilisisfacilisis.org", "555-515-1234", "1234 Main Street; Anytown, KS 66110");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(customer);
        this.mockMvc.perform(put("/customers/c04ca077-8c40-4437-b77a-41f510f3f185").content(jsonString).contentType("application/json")).andExpect(status().isOk())
                .andExpect(content().string(containsString("John")))
                .andExpect(content().string(containsString("Doe")));
    }

    @Test
    void updateCustomer_badRequest() throws Exception {
        Customer customer = new Customer("9ac775c3-a1d3-4a0e-a2df-3e4ee8b3a33a", "John", "Doe", "jdoe@test.com", "555-515-1234", "1234 Main Street; Anytown, KS 66110");
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(customer);
        this.mockMvc.perform(put("/customers/9ac775c3-a1d3-4a0e-a2df-3e4ee8b3a49a").content(jsonString).contentType("application/json")).andExpect(status().isBadRequest());
    }

    @Test
    void deleteCustomer() throws Exception {
        this.mockMvc.perform(delete("/customers/38124691-9643-4f10-90a0-d980bca0b27d")).andExpect(status().isResetContent());
    }

}