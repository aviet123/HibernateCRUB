package service;

import model.Category;
import model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Iterable<Product> findAll();
    Product findById(int id);
    void save(Product product);
    void delete(int id);
    Iterable<Product> findAllByCategory(Category category);
    Page<Product> findAll(Pageable pageable);
    Page<Product> findAllByNameContaining(String name,Pageable pageable);
}
