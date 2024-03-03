package dev_kc.productsservices.dtos;

import dev_kc.productsservices.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProductDto {
    private Long id;
    private String title;
    private String image;
    private String description;
    private String category;
    private double price;


}
