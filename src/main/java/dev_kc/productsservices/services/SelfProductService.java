package dev_kc.productsservices.services;

import dev_kc.productsservices.models.Category;
import dev_kc.productsservices.models.Product;
import dev_kc.productsservices.repositories.CategoryRepository;
import dev_kc.productsservices.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService{


    private ProductRepository productRepository;
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
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return null;
    }
}
