package mil.army.futures.asitemplate.services

import mil.army.futures.asitemplate.Product
import mil.army.futures.asitemplate.repositories.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(private val productRepository: ProductRepository) {
    fun addProduct(product: String): Product {
        return productRepository.save(Product(name = product, quantity = 0))
    }

    fun getProducts(): List<Product> {
        return productRepository.findAll()
    }
}