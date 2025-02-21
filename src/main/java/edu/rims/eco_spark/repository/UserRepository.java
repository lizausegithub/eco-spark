package edu.rims.eco_spark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.eco_spark.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}