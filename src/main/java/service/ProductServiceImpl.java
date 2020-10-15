package service;

import exception.NotFoundException;
import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import repo.ProductRepo;
import service.ProductService;

public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;


    @Override
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Product findById(int id) throws NotFoundException{
        Product product = productRepo.findOne(id);
        if(product != null){
            return product;
        }else
            throw new NotFoundException();
    }

    @Override
    public void save(Product product) {
        productRepo.save(product);
    }

    @Override
    public void delete(int id) {
        productRepo.delete(id);
    }

    @Override
    public Page<Product> findAllByCategory(Category category, Pageable pageable) {
        return productRepo.findAllByCategory(category, pageable);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public Page<Product> findAllByNameContaining(String name, Pageable pageable) {
        return productRepo.findAllByNameContaining(name,pageable);
    }
}
