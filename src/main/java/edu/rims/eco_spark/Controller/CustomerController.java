package edu.rims.eco_spark.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.rims.eco_spark.constant.WidgetStatus;
import edu.rims.eco_spark.entity.Category;
import edu.rims.eco_spark.repository.CategoryRepository;
import edu.rims.eco_spark.repository.WidgetRepository;
import org.springframework.web.bind.annotation.RequestParam;




@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private WidgetRepository widgetRepository;

    @GetMapping({ "/home", "/" })
    String home(Model model) {
        List<Category> categories = categoryRepository.findAll();

        model.addAttribute("categories", categories);
        model.addAttribute("widgets", widgetRepository.findByWidgetStatus(WidgetStatus.ACTIVE, Sort.by("sequence")));
        return "customer/home";
    }
    
     
}