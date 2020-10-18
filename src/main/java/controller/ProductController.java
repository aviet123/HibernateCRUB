//package controller;
//
//
//import exception.NotFoundException;
//import model.Category;
//import model.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.web.PageableDefault;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import service.CategoryService;
//import service.ProductService;
//
//import java.util.Optional;
//
//@Controller
//@RequestMapping("/products")
//public class ProductController {
//
//    @Autowired
//    ProductService productService;
//
//    @Autowired
//    CategoryService categoryService;
//
//    @GetMapping("")
//    public String getIndex(@PageableDefault(size = 5)Pageable pageable, @RequestParam("s") Optional<String> s, Model model){
//        Page<Product> products;
//        if (s.isPresent()){
//            products = productService.findAllByNameContaining(s.get(),pageable);
//            model.addAttribute("s",s.get());
//        }else{
//            products = productService.findAll(pageable);
//        }
//        model.addAttribute("products",products);
//        return "product/index";
//    }
//
//    @PostMapping("/search")
//   public String getProductsByCategory(@ModelAttribute Category category, Model model,@PageableDefault(size = 5) Pageable pageable){
//        model.addAttribute("products", productService.findAllByCategory(category,pageable));
//        return "product/index";
//    }
//
//    @GetMapping("/create")
//    public String showCreateForm(Model model){
//        model.addAttribute("product", new Product());
//        return "product/create";
//    }
//
//    @PostMapping("/create")
//    public String createProduct(Product product){
//        productService.save(product);
//        return "redirect:/products";
//    }
//
//    @GetMapping("/delete/{id}")
//    public String showDeleteForm(@PathVariable("id") int id, Model model) throws NotFoundException {
//        Product product = productService.findById(id);
//        model.addAttribute("product",product);
//        return "product/delete";
//    }
//
//    @PostMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable("id") int id){
//        productService.delete(id);
//        return "redirect:/products";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable("id") int id, Model model) throws NotFoundException{
//        Product product = productService.findById(id);
//        model.addAttribute("product",product);
//        return "product/edit";
//    }
//
//
//    @PostMapping("/update")
//    public String editProduct(Product product, Model model){
//        productService.save(product);
//        return "redirect:/products";
//    }
//
//    @ModelAttribute("categories")
//    public Iterable<Category> getCategories(){
//        return categoryService.findAll();
//    }
//
//    @ExceptionHandler(NotFoundException.class)
//    public String showNotFoundException(){
//        return "notfound";
//    }
//
//
//}
