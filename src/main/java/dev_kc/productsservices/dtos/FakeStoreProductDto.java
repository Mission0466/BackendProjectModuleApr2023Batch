package dev_kc.productsservices.dtos;

import dev_kc.productsservices.models.Category;
import dev_kc.productsservices.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;

    public Product toProduct() {
        Product product = new Product();

        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageURL(image);

        Category productCategory = new Category();
        productCategory.setTitle(category);

        product.setCategory(productCategory);

        return product;

    }

}
