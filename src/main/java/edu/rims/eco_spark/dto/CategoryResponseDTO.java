package edu.rims.eco_spark.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponseDTO {
    private String categoryId;
    private String categoryTitle;
    private String categoryDescription;
    private String categoryImageUrl;
    private String categoryStatus;
}
