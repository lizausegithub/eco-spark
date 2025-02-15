package edu.rims.eco_spark.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequestMapping("/logIn")
public class LoginController {
    @GetMapping("/home")
    String home() {
        return "logIn/home";
    }

    @GetMapping("/sign")
    String sign() {
        return "logIn/sign";
    }
}
