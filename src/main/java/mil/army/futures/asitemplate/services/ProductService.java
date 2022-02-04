package mil.army.futures.asitemplate.services;

import lombok.AllArgsConstructor;
import mil.army.futures.asitemplate.Product;
import mil.army.futures.asitemplate.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {

    ProductRepository productRepository;

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
