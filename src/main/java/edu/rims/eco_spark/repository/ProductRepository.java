package edu.rims.eco_spark.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.eco_spark.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByProductTitleContainingIgnoreCase(String productTitle);

}