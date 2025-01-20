package edu.rims.eco_spark.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class ProductController {
    @GetMapping({"/home","/"})
    String home(){
        return "home";
    }
}