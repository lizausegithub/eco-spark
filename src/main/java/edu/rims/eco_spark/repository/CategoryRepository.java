package edu.rims.eco_spark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.eco_spark.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
