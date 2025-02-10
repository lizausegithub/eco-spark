package edu.rims.eco_spark.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.rims.eco_spark.entity.Category;
import edu.rims.eco_spark.repository.CategoryRepository;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping({ "/home", "/" })
    String home(Model model) {
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("categories", categories);
        return "customer/home";
    }
}