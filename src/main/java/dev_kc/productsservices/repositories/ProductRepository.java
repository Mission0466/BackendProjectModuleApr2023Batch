package dev_kc.productsservices.repositories;

import dev_kc.productsservices.models.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);


    List<Product> findAll();


    Product findByIdIs(Long id);

    @Transactional
    void deleteProductById(Long id);

    List<Product>  findByCategoryTitle(String title);
}
