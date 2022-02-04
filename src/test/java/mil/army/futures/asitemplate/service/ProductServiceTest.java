package mil.army.futures.asitemplate.service;

import mil.army.futures.asitemplate.Product;
import mil.army.futures.asitemplate.services.ProductService;
import mil.army.futures.asitemplate.repositories.ProductRepository;
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
        List<Product> expectedProducts = List.of(new Product(1L, "first-product"), new Product(2L, "second-product"));
        when(productRepository.findAll()).thenReturn(expectedProducts);

        List<Product> actualProducts = productService.getProducts();

        assertThat(actualProducts).isEqualTo(expectedProducts);
    }

    @Test
    void shouldCreateANewProduct(){
        Product productToSave = new Product(1L, "new-product");

        productService.addProduct(productToSave);

        verify(productRepository).save(productToSave);
    }
}
