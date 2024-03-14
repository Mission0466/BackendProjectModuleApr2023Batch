package dev_kc.productsservices.controllers;


import dev_kc.productsservices.dtos.CreateProductRequestDto;
import dev_kc.productsservices.dtos.UpdateProductDto;
import dev_kc.productsservices.models.Product;
import dev_kc.productsservices.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class ProductController {

//    private ProductService productService = new FakeStoreProductService();
//    private ProductService productService2 = new FakeStoreProductService();

    private ProductService productService;

    private RestTemplate restTemplate;

    public ProductController(@Qualifier("selfProductService") ProductService productService, RestTemplate restTemplate){  // constructor

        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    //post /products
    // requestBody
//    {
//        title:
//        description:
//        price:
//    }
    @PostMapping("/products")   //to create a product we are using PostMapping annotation to come and use this method
    public Product createProduct(@RequestBody CreateProductRequestDto request ){
        return productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()
        );
    }

    @GetMapping("/products/{id}")
    public Product getProductDetails(@PathVariable("id") Long productId){
        return productService.getSingleProduct(productId);
    }

    @GetMapping("/products")
    public List<Product> getAllProduct(){

        return productService.getProducts();
    }

//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getAllProduct(){
//        List<Product> products = productService.getProducts();
//        ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatus.FOUND);
//        return response;
//    }
    @GetMapping("/products/categories")
    public String[] getAllCategories(){
       return restTemplate.getForObject("https://fakestoreapi.com/products/categories", String[].class);

    }



    @PatchMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Long productId, @RequestBody UpdateProductDto request){
        return productService.updateProduct(
                productId,
                request.getTitle(),
                request.getPrice(),
                request.getCategory(),
                request.getDescription(),
                request.getImage()
        );
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable("id") Long productId){
         productService.deleteProduct(productId);
    }

    @GetMapping("/products/category/{category}") // to get the product details we are using getmapping annotation come and use this method
    public List<Product> getProductByCategory(@PathVariable("category") String category){   // in the request URL there is a parameter called id so get the value and pass into the productId therefore using PathVariable annotation
        return productService.getProductByCategory(category);
    }

}
