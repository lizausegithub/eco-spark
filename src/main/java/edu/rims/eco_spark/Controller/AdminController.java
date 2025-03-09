package edu.rims.eco_spark.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.rims.eco_spark.entity.Category;
import edu.rims.eco_spark.entity.Product;
import edu.rims.eco_spark.repository.CategoryRepository;
import edu.rims.eco_spark.repository.ProductRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository ProductRepository;

    @GetMapping("/dashboard")
    String dashborad() {
        return "admin/dashboard";
    }

    @GetMapping("/category")
    String adminCategory(Model model) {
        List<Category> categories = categoryRepository.findAll();
        // System.out.println(categories.size());
        model.addAttribute("categories", categories);
        return "admin/category";
    }

    @PostMapping("/category")
    public String categoryAdd(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/product")
    String product(Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Product> products = ProductRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "admin/product";
    }

    @PostMapping("/product")
    public String productAdd(@ModelAttribute Product product, @RequestParam String categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        product.setCategory(category);
        ProductRepository.save(product);
        return "redirect:/admin/product";
    }
    

    @GetMapping("/edit")
    public String edit(@RequestParam String id, Model model) {
        Category category = categoryRepository.findById(id).orElseThrow();
        model.addAttribute("category", category);
        // System.out.println(category.getCategoryTitle());
        return "admin/edit";
    }

}
