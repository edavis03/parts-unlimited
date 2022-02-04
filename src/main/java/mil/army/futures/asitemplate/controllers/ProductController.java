package mil.army.futures.asitemplate.controllers;

import mil.army.futures.asitemplate.Product;
import mil.army.futures.asitemplate.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public @ResponseBody
    List<String> getProducts() {
        List<String> products = new ArrayList<>();
        for(Product product : productService.getProducts()){
            products.add(product.getName());
        }
        return products;
    }

    @PostMapping("/products")
    public ResponseEntity<String> addProduct(@RequestBody String product) {
        var savedProduct = productService.addProduct(new Product(product));
        URI location = createResourceLocation("/products",savedProduct.getId());
        return ResponseEntity.created(location).body(savedProduct.getName());
    }

    private URI createResourceLocation(String path, Long resourceId) {
        return ServletUriComponentsBuilder.fromCurrentRequestUri().port("8080").path(path)
                .buildAndExpand(resourceId).toUri();
    }
}