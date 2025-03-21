package edu.rims.eco_spark.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import edu.rims.eco_spark.constant.OrderStatus;
import edu.rims.eco_spark.entity.Order;
import edu.rims.eco_spark.repository.OrderRepository;



@Controller
public class OrderController {
@Autowired
private OrderRepository orderRepository;

    @GetMapping("/customer/orderHistory")
    String orderHistory(Principal principal, Model model) {
        List<Order> orders = orderRepository.findByUserUserEmailAndOrderStatusNot(principal.getName(), OrderStatus.CART);
        model.addAttribute("orders", orders);
       return "/customer/orderHistory";
   }
    
}
