package edu.rims.eco_spark.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.rims.eco_spark.constant.OrderStatus;
import edu.rims.eco_spark.entity.Order;

public interface OrderRepository extends JpaRepository<Order, String>{

    Optional<Order> findByUserUserIdAndOrderStatus(Integer userId, OrderStatus status);

    List<Order> findByUserUserEmailAndOrderStatusNot(String email, OrderStatus status);
    
}
