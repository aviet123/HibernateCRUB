package controller;


import exception.NotFoundException;
import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import service.CategoryService;
import service.ProductService;

@RestController
public class CategoryRestController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/categories/", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Category>> findAllProduct() {
        Iterable<Category> products = categoryService.findAll();
        if (products == null) {
            return new ResponseEntity<Iterable<Category>>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<Iterable<Category>>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> findProductById(@PathVariable("id") Long id) throws NotFoundException {
        Category category = categoryService.findById(id);
        if (category == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<Category>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/categories/", method = RequestMethod.POST)
    public ResponseEntity<Void> createProduct(@RequestBody Category category, UriComponentsBuilder ucBuilder) {
        System.out.println("create new product");
        categoryService.save(category);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/categories/{id}").buildAndExpand(category.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Category> updateProduct(@PathVariable("id") Long id, @RequestBody Category category) throws NotFoundException {
        System.out.println("update category with id is " + id);
        Category category1 = categoryService.findById(id);

        if (category1 == null) {
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        } else {
            category1.setName(category.getName());
            category1.setId(category.getId());
            categoryService.save(category1);
            return new ResponseEntity<Category>(category1, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/categories/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Category> deleteProduct(@PathVariable("id") Long id) throws NotFoundException {
        Category category = categoryService.findById(id);
        if (category == null) {
            System.out.println("cannot delete category with " + id + " cuz category cannot found");
            return new ResponseEntity<Category>(HttpStatus.NOT_FOUND);
        } else {
            categoryService.delete(id);
            return new ResponseEntity<Category>(HttpStatus.NO_CONTENT);
        }
    }
}
