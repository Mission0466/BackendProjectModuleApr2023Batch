package dev_kc.productsservices.services;


import dev_kc.productsservices.models.AllCategories;
import dev_kc.productsservices.models.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId);

    List<Product> getProducts();
    Product createProduct(String title,
                          String description,
                          String category,
                          double price,
                          String image);


    Product updateProduct(
            Long productId,
          String title,
          double price,
          String description,
          String image,
          String category
    );

    void deleteProduct(Long productId);

    List<Product> getProductByCategory(String category);
}
