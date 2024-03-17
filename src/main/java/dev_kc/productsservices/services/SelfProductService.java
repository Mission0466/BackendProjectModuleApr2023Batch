package dev_kc.productsservices.services;

import dev_kc.productsservices.models.Category;
import dev_kc.productsservices.models.Product;
import dev_kc.productsservices.repositories.CategoryRepository;
import dev_kc.productsservices.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(Long productId) {

        return productRepository.findByIdIs(productId);
    }

    @Override
    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    @Override
    public Product createProduct(String title, String description, String category, double price, String image) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageURL(image);

        Category categoryFromDatabase = categoryRepository.findByTitle(category);

        if(categoryFromDatabase == null){
            Category newCategory = new Category();
            newCategory.setTitle(category);
            categoryFromDatabase = newCategory;
        }

        product.setCategory(categoryFromDatabase);

        Product savedProduct =productRepository.save(product);

        return savedProduct;
    }

    @Override
    public Product updateProduct(Long productId, String title, double price, String description, String image, String category) {
       // step 1:
        // first search the id of the product if exists then do the update the product attributes
        Product p = productRepository.findByIdIs(productId);
        if(p != null) {
            if (title != null) {
                p.setTitle(title);
            }
            if (price != 0) {
                p.setPrice(price);
            }
            if (description != null) {
                p.setDescription(description);
            }
            if (image != null) {
                p.setImageURL(image);
            }
            if (category != null) {
                Category cat = categoryRepository.findByTitle(category);
                p.setCategory(cat);

            }

        }
        return productRepository.save(p);


    }

    @Override
    public void deleteProduct(Long productId) {

        productRepository.deleteProductById(productId);
    }

    @Override
    public List<Product> getProductByCategory(String title) {
       return productRepository.findByCategoryTitle(title);

    }
}
