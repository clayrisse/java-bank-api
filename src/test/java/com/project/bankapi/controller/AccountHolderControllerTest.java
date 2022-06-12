package com.project.bankapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.bankapi.service.impl.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class AccountHolderControllerTest {

    @Autowired    AccountHolderService accountHolderService;
    @Autowired    AdminService adminService;
    @Autowired    CheckingAcService checkingAcService;
    @Autowired    CheckStudentAcService checkStudentAcService;
    @Autowired    CreditCardService creditCardService;
    @Autowired    SavingsAcService service;
    @Autowired    ThirdPartyService thirdPartyService;
    @Autowired    AccountService accountService;
    @Autowired    ProductService productService;


    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

//    private Long idProduct1;
//    private Long idProduct2;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        Product product = new Product("Coca Cola", "Bebida con sabor a cola", 2.6);
//        Product product2 = new Product("Fanta", "Bebida con sabor a naranja", 2.6);
//        productRepository.saveAll(List.of(product, product2));
//        idProduct1 = productRepository.findAll().get(0).getId();
//        idProduct2 = productRepository.findAll().get(1).getId();
    }

    @AfterEach
    void destroy() {
//        productRepository.deleteAll();
    }

//    @Test
//    void getById_idExists_resultOK() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(get("/api/products/" + idProduct1)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andReturn();
//        assertTrue(mvcResult.getResponse().getContentAsString().contains("Coca Cola"));
//    }
}
