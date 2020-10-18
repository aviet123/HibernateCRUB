package controller;


import exception.NotFoundException;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import service.ProductService;

@RestController
public class ProductRestController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/products/", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Product>> findAllProduct() {
        Iterable<Product> products = productService.findAll();
        if (products == null) {
            return new ResponseEntity<Iterable<Product>>(HttpStatus.NO_CONTENT);
        } else
            return new ResponseEntity<Iterable<Product>>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> findProductById(@PathVariable("id") Integer id) throws NotFoundException {
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        } else
            return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/", method = RequestMethod.POST)
    public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
        System.out.println("create new product");
        productService.save(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Integer id, @RequestBody Product product) throws NotFoundException {
        System.out.println("update product with id is " + id);
        Product updatedProduct = productService.findById(id);

        if (updatedProduct == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        } else {
            updatedProduct.setName(product.getName());
            updatedProduct.setPrice(product.getPrice());
            updatedProduct.setCategory(product.getCategory());
            productService.save(updatedProduct);

            return new ResponseEntity<Product>(updatedProduct, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") Integer id) throws NotFoundException {
        Product product = productService.findById(id);
        if (product == null) {
            System.out.println("cannot delete product with " + id + " cuz product cannot found");
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        } else {
            productService.delete(id);
            return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
        }
    }
}
