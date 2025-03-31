package edu.rims.eco_spark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.eco_spark.entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User>  findByUserEmail(String email);

}