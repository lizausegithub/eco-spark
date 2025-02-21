package edu.rims.eco_spark.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.rims.eco_spark.entity.Category;
import edu.rims.eco_spark.repository.CategoryRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category/home")
    String getProductByCategoryId(@RequestParam("category") String categoryId, Model model) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        model.addAttribute("category", category);
        model.addAttribute("categories", categoryRepository.findAll());
        return "product/home";
    }

    @GetMapping("/pdp")
    String pdp() {
        return "product/pdp";
    }

}