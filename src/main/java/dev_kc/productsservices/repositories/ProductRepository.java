package dev_kc.productsservices.repositories;

import dev_kc.productsservices.models.Product;
import dev_kc.productsservices.repositories.projections.ProductWithIdAndTitle;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product save(Product p);


    List<Product> findAll();


    Product findByIdIs(Long id);

    @Transactional
    void deleteProductById(Long id);

    List<Product>  findByCategoryTitle(String title);


    //HQL queries
    @Query("select p from Product p where p.category.title = :title and p.id = :productId")
    Product getTheProductWithParticularName(@Param("title") String title, @Param("productId") Long productId);

    @Query("select p.title as title, p.id as id from Product p where p.category.id = :categoryId")
    List<ProductWithIdAndTitle> getTitleOfProductsOfGivenCategory(@Param("categoryId") Long categoryId);
}
