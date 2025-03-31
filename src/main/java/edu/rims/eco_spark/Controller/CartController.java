package edu.rims.eco_spark.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import edu.rims.eco_spark.constant.OrderStatus;
import edu.rims.eco_spark.entity.Order;
import edu.rims.eco_spark.entity.OrderItem;
import edu.rims.eco_spark.entity.Product;
import edu.rims.eco_spark.entity.User;
import edu.rims.eco_spark.repository.OrderRepository;
import edu.rims.eco_spark.repository.ProductRepository;
import edu.rims.eco_spark.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CartController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/product/cart")
    String cart(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.getUser(username);
        Order order = orderRepository.findByUserUserIdAndOrderStatus(user.getUserId(), OrderStatus.CART).orElse(null);
        model.addAttribute("order", order);
        return "product/cart";
    }

    @GetMapping("/product/cart/add")
    String cart(@RequestParam String productId, Principal principal) {
        String username = principal.getName();
        User user = userService.getUser(username);
        Order order = orderRepository.findByUserUserIdAndOrderStatus(user.getUserId(), OrderStatus.CART)
                .orElse(new Order());
        order.setUser(user);

        Product product = productRepository.findById(productId).orElseThrow();

        OrderItem orderItem = new OrderItem(product);
        order.addOrderItem(orderItem);

        orderRepository.save(order);
        return "redirect:/product/cart";
    }

    @GetMapping("/product/cart/remove")
    public String removeItem(@RequestParam("orderItem") String orderItemId, Principal principal) {
        String username = principal.getName();
        User user = userService.getUser(username);
        Order order = orderRepository.findByUserUserIdAndOrderStatus(user.getUserId(), OrderStatus.CART).orElse(null);
        order.removeOrderItem(orderItemId);
        orderRepository.save(order);
        return "redirect:/product/cart";
    }

    @GetMapping("/customer/placeorder")
    String placeorder(Principal principal) {
        String username = principal.getName();
        User user = userService.getUser(username);
        Order order = orderRepository.findByUserUserIdAndOrderStatus(user.getUserId(), OrderStatus.CART).orElse(null);
        order.setOrderStatus(OrderStatus.DELIVERED);
        orderRepository.save(order);
        return "customer/placeorder";
    }

}
