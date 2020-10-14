package controller;


import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repo.CategoryRepo;
import service.CategoryService;
import service.ProductService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

//    @GetMapping("/views/{id}")
//    public String viewCategory(@PathVariable("id") Long id,Model model){
//        Category category = categoryService.findById(id);
//        Iterable<Product> products = productService.findAllByCategory(category);
//        model.addAttribute("category",category);
//        model.addAttribute("products",products);
//        return "category/view";
//    }

    @GetMapping("")
    public String getIndex(Model model){
       Iterable<Category> categories = categoryService.findAll();
       model.addAttribute("categories", categories);
       return "/category/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model){
        model.addAttribute("category", new Category());
        return "/category/create";
    }

    @PostMapping("/create")
    public String createCategory(Category category, RedirectAttributes redirectAttributes){
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message","create successfully");
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String showDeleteForm(@PathVariable("id") Category category, Model model){
        model.addAttribute("category",category);
        return "/category/delete";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id, Model model){
        categoryService.delete(id);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Category category,Model model){
        model.addAttribute("category",category);
        return "/category/edit";
    }

    @PostMapping("/edit")
    public String editCategory(Category category ,RedirectAttributes redirectAttributes){
        categoryService.save(category);
        redirectAttributes.addFlashAttribute("message","edit successfully");
        return "redirect:/categories";
    }

}
