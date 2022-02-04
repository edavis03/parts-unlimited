package org.asi.partsunlimited.controllers;


import org.asi.partsunlimited.Product;
import org.asi.partsunlimited.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void shouldSaveProductWhenANewProductIsAdded() throws Exception {
        when(productService.addProduct("some-product")).thenReturn(new Product(1L, "some-product", 0));

        this.mockMvc.perform(post("/products").contentType(MediaType.TEXT_PLAIN).content("some-product"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("some-product"))
                .andExpect(jsonPath("$.quantity").value("0"));
    }

    @Test
    void shouldRetrieveAllProductsWhenGettingProducts() throws Exception {
        when(productService.getProducts()).thenReturn(List.of(
                new Product(1L, "first-product", 0),
                new Product(2L, "second-product", 1)));

        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("first-product"))
                .andExpect(jsonPath("$[0].quantity").value("0"))
                .andExpect(jsonPath("$[1].name").value("second-product"))
                .andExpect(jsonPath("$[1].quantity").value("1"));

        verify(productService).getProducts();
    }
}