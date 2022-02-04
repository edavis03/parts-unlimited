package org.asi.partsunlimited.repositories;

import org.asi.partsunlimited.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
