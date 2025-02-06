package edu.rims.eco_spark.entity;

import edu.rims.eco_spark.constant.CategoryStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "category")
@Setter
@Getter
public class Category extends Auditable {

    @Id
    @Column(name = "category_id", nullable = false, length = 255)
    private String categoryId;

    @Column(name = "category_title", nullable = false, length = 50)
    private String categoryTitle;

    @Column(name = "category_description", columnDefinition = "TEXT")
    private String categoryDescription;

    @Column(name = "category_image_url")
    private String categoryImageUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_status", columnDefinition = "ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE'")
    private CategoryStatus categoryStatus = CategoryStatus.ACTIVE;
}
