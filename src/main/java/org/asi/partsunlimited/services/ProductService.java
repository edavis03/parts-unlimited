package org.asi.partsunlimited.services;

import lombok.AllArgsConstructor;
import org.asi.partsunlimited.Product;
import org.asi.partsunlimited.repositories.ProductRepository;
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
