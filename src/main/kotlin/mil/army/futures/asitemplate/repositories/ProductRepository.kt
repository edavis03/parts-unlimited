package mil.army.futures.asitemplate.repositories

import mil.army.futures.asitemplate.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
