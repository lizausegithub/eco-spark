package edu.rims.eco_spark.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    @GetMapping({ "/home", "/" })
    String home() {
        return "product/home";
    }
}