package org.asi.partsunlimited.service;

import org.asi.partsunlimited.Product;
import org.asi.partsunlimited.repositories.ProductRepository;
import org.asi.partsunlimited.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ProductServiceTest {

    @MockBean
    ProductRepository productRepository;

    ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository);
    }

    @Test
    void shouldRetrieveAllProducts(){
        List<Product> expectedProducts = List.of(new Product(1L, "first-product", 0), new Product(2L, "second-product", 0));
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.getProducts();

        assertThat(actualProducts).isEqualTo(expectedProducts);
    }

    @Test
    void shouldCreateANewProduct(){
        Product productToSave = new Product(1L, "new-product", 0);

        productService.addProduct(productToSave);

        verify(productRepository).save(productToSave);
    }
}
