package edu.rims.eco_spark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.eco_spark.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

}