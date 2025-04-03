package edu.rims.eco_spark.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.rims.eco_spark.entity.Category;
import edu.rims.eco_spark.entity.Product;
import edu.rims.eco_spark.repository.CategoryRepository;
import edu.rims.eco_spark.repository.ProductRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository ProductRepository;

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

    @GetMapping("/product/pdp")
    String getProductByProductId(@RequestParam("product") String productId, Model model) {
        Product product = ProductRepository.findById(productId).orElseThrow();
        model.addAttribute("product", product);
        return "product/pdp";
    }

    @GetMapping("/search")
    String searchProduct(@RequestParam("search") String productTitle, Model model) {
        List<Product> products = ProductRepository.findByProductTitleContainingIgnoreCase(productTitle);
        model.addAttribute("products", products);
        return "product/home";
    }

    @GetMapping(value = "/image/{categoryId}", produces = { "image/jpg", "image/png", "image/jpeg" })
    @ResponseBody
    byte[] getCategoryImage(@PathVariable String categoryId) throws IOException {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        String categoryImageUrl = category.getCategoryImageUrl();
        if (categoryImageUrl != null && categoryImageUrl.startsWith("http")) {
            return null;
        }
        FileInputStream fis = new FileInputStream(categoryImageUrl);
        return fis.readAllBytes();
    }

    @GetMapping(value = "productimage/{productId}", produces = { "image/jpg", "image/png", "image/jpeg" })
    @ResponseBody
    byte[] getProductImage(@PathVariable String productId) throws IOException {
        Product product = ProductRepository.findById(productId).orElseThrow();
        String productImageUrl = product.getProductImageUrl();
        if (productImageUrl != null && productImageUrl.startsWith("http")) {
            return null;
        }
        FileInputStream fis = new FileInputStream(productImageUrl);
        return fis.readAllBytes();
    }

}