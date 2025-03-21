package edu.rims.eco_spark.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseDTO {
    private String productId;
    private String productTitle;
    private String productDescription;
    private double productPrice;
    private int productStockQuantity;
    private String productImageUrl;
    private CategoryResponse category;
    @Setter
    @Getter
    public class CategoryResponse {
        private String categoryId;
        private String categoryTitle;
    }
}
