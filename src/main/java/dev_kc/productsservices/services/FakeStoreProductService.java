package dev_kc.productsservices.services;

import dev_kc.productsservices.dtos.FakeStoreProductDto;
import dev_kc.productsservices.dtos.UpdateProductDto;
import dev_kc.productsservices.models.AllCategories;
import dev_kc.productsservices.models.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) {
        FakeStoreProductDto fakeStoreProduct = restTemplate.getForObject("Https://fakestoreapi.com/products/" + productId , FakeStoreProductDto.class); // getting the response from fakestore URL and saving those in the DTO class
        return fakeStoreProduct.toProduct();
    }

    @Override
    public List<Product> getProducts() {

        List<Product> productList = new ArrayList<>();


        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products", FakeStoreProductDto[].class);
        for(int i=0; i< fakeStoreProductDtos.length; i++ ){
            productList.add(fakeStoreProductDtos[i].toProduct());
        }
        return productList;
    }





    @Override
    public Product createProduct(String title,
                                 String description,
                                 String category,
                                 double price,
                                 String image
                                 ){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setDescription(description);

        FakeStoreProductDto  response = restTemplate.postForObject(
                                   "Https://fakestoreapi.com/products",
                                        fakeStoreProductDto,  // req body send this object
                                        FakeStoreProductDto.class // data type of response
                                        );
        return response.toProduct();
    }

    @Override
    public Product updateProduct(Long productId,
                                 String title,
                                 double price,
                                 String description,
                                 String image,
                                 String category ){


        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(productId);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setCategory(category);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setDescription(description);



        // Fetch and return the updated product
        FakeStoreProductDto  updatedProductDto  = restTemplate.patchForObject(
                "Https://fakestoreapi.com/products/" + productId , fakeStoreProductDto, FakeStoreProductDto.class
        );

        return updatedProductDto.toProduct();

    }

    @Override
    public void deleteProduct(Long productId) {
        restTemplate.delete("Https://fakestoreapi.com/products/" + productId );

    }

    @Override
    public List<Product> getProductByCategory(String category){
        List<Product> productList = new ArrayList<>();


        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/category/" + category, FakeStoreProductDto[].class);
        for(int i=0; i< fakeStoreProductDtos.length; i++ ){
            productList.add(fakeStoreProductDtos[i].toProduct());
        }
        return productList;
    }
}
