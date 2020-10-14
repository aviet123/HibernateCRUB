package repo;

import model.Category;
import model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepo extends PagingAndSortingRepository<Product, Integer> {
        Page<Product> findAllByCategory(Category category, Pageable pageable);

        Page<Product> findAll(Pageable pageable);

        Page<Product> findAllByNameContaining(String name,Pageable pageable);
}
