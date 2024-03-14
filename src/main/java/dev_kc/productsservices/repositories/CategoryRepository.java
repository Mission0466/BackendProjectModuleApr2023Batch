package dev_kc.productsservices.repositories;

import dev_kc.productsservices.models.Category;
import dev_kc.productsservices.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByTitle(String title);
    Category save(Category category);
}
