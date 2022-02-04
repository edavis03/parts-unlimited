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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void shouldSaveProductWhenANewProductIsAdded() throws Exception {
        when(productService.addProduct(new Product("some-product"))).thenReturn(new Product(1L, "some-product"));

        this.mockMvc.perform(post("/products").contentType(MediaType.TEXT_PLAIN).content("some-product"))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("some-product")));

        verify(productService).addProduct(new Product("some-product"));
    }

    @Test
    void shouldRetrieveAllProductsWhenGettingProducts() throws Exception {
        when(productService.getProducts()).thenReturn(List.of(new Product(1L, "first-product"), new Product(2L, "second-product")));

        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("first-product")))
                .andExpect(content().string(containsString("second-product")));

        verify(productService).getProducts();
    }
}