package edu.rims.eco_spark.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/home")
    String order() {
        return "order/home";
    }
    
}
