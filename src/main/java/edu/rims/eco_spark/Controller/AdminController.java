package edu.rims.eco_spark.Controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import edu.rims.eco_spark.constant.WidgetStatus;
import edu.rims.eco_spark.dto.ProductResponseDTO;
import edu.rims.eco_spark.dto.ProductResponseDTO.CategoryResponse;
import edu.rims.eco_spark.entity.Category;
import edu.rims.eco_spark.entity.Order;
import edu.rims.eco_spark.entity.Product;
import edu.rims.eco_spark.entity.Widget;
import edu.rims.eco_spark.repository.CategoryRepository;
import edu.rims.eco_spark.repository.OrderRepository;
import edu.rims.eco_spark.repository.ProductRepository;
import edu.rims.eco_spark.repository.WidgetRepository;
import edu.rims.eco_spark.service.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private WidgetRepository widgetRepository;

    @Autowired
    private OrderRepository orderRepository;

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
    public String categoryAdd(@ModelAttribute Category category, @RequestParam("categoryImage") MultipartFile file)
            throws Exception {

        if (!file.isEmpty()) {
            String fileName = categoryService.ImageUpload(file);
            category.setCategoryImageUrl(fileName);
        }
        categoryRepository.save(category);
        return "redirect:/admin/category";
    }

    @GetMapping("/product")
    String product(Model model) {
        List<Category> categories = categoryRepository.findAll();
        List<Product> products = productRepository.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        return "admin/product";
    }

    @PostMapping("/product")
    public String productAdd(@ModelAttribute Product product, @RequestParam String categoryId,
            @RequestParam(value = "productImage", required = false) MultipartFile file,
            @RequestParam(required = false) String imageUrl)
            throws Exception {
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        product.setCategory(category);

        if (imageUrl != null) {
            product.setProductImageUrl(imageUrl);
        }

        if (file != null && !file.isEmpty()) {
            String fileName = categoryService.ImageUpload(file);
            product.setProductImageUrl(fileName);
        }

        product.setCreatedDate(LocalDateTime.now());
        product.setUpdatedDate(LocalDateTime.now());

        productRepository.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/product/remove")
    public String removeProduct(@RequestParam("product") String productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        productRepository.save(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam String id, Model model) {
        Category category = categoryRepository.findById(id).orElseThrow();
        model.addAttribute("category", category);
        // System.out.println(category.getCategoryTitle());
        return "admin/edit";
    }

    @GetMapping("/order")
    String adminOrder(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "admin/order";
    }

    @GetMapping("/customer")
    String adminCustomer() {
        return "admin/customer";
    }

    @GetMapping("/widget")
    public String getWidgets(Model model) {
        model.addAttribute("widgets", widgetRepository.findAll());
        return "admin/widget";
    }

    @PostMapping("/widget/add")
    public String postMethodName(@RequestParam String widgetName, @RequestParam String widgetId,
            @RequestParam int sequence) {
        Widget widget = widgetRepository.findById(widgetId).orElse(new Widget());
        widget.setWidgetName(widgetName);
        widget.setSequence(sequence);
        widget.setUpdatedDate(LocalDateTime.now());
        widgetRepository.save(widget);
        return "redirect:/admin/widget";
    }

    @GetMapping("/widget/remove")
    public String removeWidget(@RequestParam("id") String widgetId) {
        Widget widget = widgetRepository.findById(widgetId).orElseThrow();
        widget.setWidgetStatus(WidgetStatus.INACTIVE);
        widgetRepository.save(widget);
        return "redirect:/admin/widget";
    }

    @GetMapping("/widget/edit")
    public String editWidget(@RequestParam("id") String widgetId, Model model) {
        Widget widget = widgetRepository.findById(widgetId).orElseThrow();
        model.addAttribute("widget", widget);
        model.addAttribute("widgets", widgetRepository.findAll());
        return "admin/widget";
    }

    @GetMapping(value = "/image/{categoryId}", produces = { "image/jpg", "image/jpeg", "image/png" })
    @ResponseBody
    public byte[] getImage(@PathVariable String categoryId) throws IOException {

        Category category = categoryRepository.findById(categoryId).orElseThrow();
        String categoryImageUrl = category.getCategoryImageUrl();
        if (categoryImageUrl == null || categoryImageUrl.startsWith("http")) {
            return null;
        }
        FileInputStream fis = new FileInputStream(categoryImageUrl);

        return fis.readAllBytes();
    }

    @GetMapping(value = "/productimage/{productId}", produces = { "image/jpg", "image/jpeg", "image/png" })
    @ResponseBody
    public byte[] getProductImage(@PathVariable String productId) throws IOException {

        Product product = productRepository.findById(productId).orElseThrow();
        String productImageUrl = product.getProductImageUrl();
        if (productImageUrl == null || productImageUrl.startsWith("http")) {
            return null;
        }
        FileInputStream fis = new FileInputStream(productImageUrl);
        return fis.readAllBytes();
    }

    @PostMapping("/widget/product/add")
    public String addProductToWidget(@RequestParam MultipartFile file) {

        if (file.isEmpty())
            return "redirect:/admin/widget";

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            Map<String, String> details = new HashMap<>();

            // for header
            bufferedReader.readLine();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                processDetails(split[0], split[1]);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/admin/widget";
    }

    @GetMapping("/widget/products")
    public String getMethodName(@RequestParam("id") String wigetId, Model model) {
        Widget widget = widgetRepository.findById(wigetId).orElseThrow();
        model.addAttribute("widget", widget);
        return "admin/widget-product";
    }

    private void processDetails(String widgetId, String productId) {
        Product product = productRepository.findById(productId).orElse(null);
        Widget widget = widgetRepository.findById(widgetId).orElse(null);

        if (product != null && widget != null) {
            if (!widget.getProducts().contains(product)) {
                widget.addProduct(product);
                widgetRepository.save(widget);
            }
        }
    }

    @GetMapping("/widget/product/remove")
    public String getMethodName(@RequestParam String widgetId, @RequestParam String productId) {
        Widget widget = widgetRepository.findById(widgetId).orElseThrow();

        widget.removeProduct(productId);

        widgetRepository.save(widget);
        return "redirect:/admin/widget";
    }

    @GetMapping("/products/{productId}")
    @ResponseBody
    public ProductResponseDTO getProduct(@PathVariable String productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        ProductResponseDTO dto = new ProductResponseDTO();
        dto.setProductId(productId);
        dto.setProductTitle(product.getProductTitle());
        dto.setProductDescription(product.getProductDescription());
        dto.setProductPrice(product.getProductPrice());
        dto.setProductStockQuantity(product.getProductStock());
        dto.setProductImageUrl(product.getProductImageUrl());

        CategoryResponse category = dto.new CategoryResponse();
        category.setCategoryId(product.getCategory().getCategoryId());
        category.setCategoryTitle(product.getCategory().getCategoryTitle());
        dto.setCategory(category);

        return dto;
    }

}
