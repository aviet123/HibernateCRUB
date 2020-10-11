package controller;


import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.product.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("")
    public String getIndex(Model model){
        Iterable<Product> products = productService.findAll();
        model.addAttribute("products",products);
        return "index";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model){
        model.addAttribute("product", new Product());
        return "create";
    }

    @PostMapping("/create")
    public String createProduct(Product product){
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable("id") int id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id){
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model){
        Product product = productService.findById(id);
        model.addAttribute("product",product);
        return "edit";
    }
    @PostMapping("/update")
    public String editProduct(Product product, Model model){
        productService.save(product);
        return "redirect:/products";
    }
}
