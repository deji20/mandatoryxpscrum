package com.group.mandatoryxpscrum.controllers;

import com.group.mandatoryxpscrum.data.services.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ProductService productService;

    @Test
    void testProductIndexGetMappingResponseNotNull() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/products");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertNotNull(result.getResponse());
    }

    @Test
    void testProductCartGetMappingResponseNotNull() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/products/cart");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertNotNull(result.getResponse());
    }

    @Test
    void testManagerProductGetMappingResponseNotNull() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/manager/products/");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertNotNull(result.getResponse());
    }

    @Test
    void testManagerProductNewPostMappingResponseNotNull() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/manager/products/new");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertNotNull(result.getResponse());
    }

    @Test
    void testManagerProductDeleteGetMappingResponseResponseNotNull() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.get("/manager/products/delete");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertNotNull(result.getResponse());
    }

    @Test
    void testManagerProductUpdatePostMappingResponseNotNull() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders.post("/manager/products/update");
        MvcResult result = mockMvc.perform(request).andReturn();
        assertNotNull(result.getResponse());
    }


}