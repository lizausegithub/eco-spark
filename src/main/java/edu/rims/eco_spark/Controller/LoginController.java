package edu.rims.eco_spark.Controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.rims.eco_spark.entity.User;
import edu.rims.eco_spark.repository.UserRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/logIn")
public class LoginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    String home() {
        return "logIn/home";
    }

    @GetMapping("/sign")
    String sign() {
        return "logIn/sign";
    }

    @PostMapping("/sign")
    public String signUp(@ModelAttribute User user) {
        user.setCreatedDate(LocalDateTime.now());
        user.setUpdatedDate(LocalDateTime.now());
        user.setCreatedBy("user");
        user.setUpdatedBy("user");
        userRepository.save(user);
        return "redirect:/logIn/sign";
    }

}
