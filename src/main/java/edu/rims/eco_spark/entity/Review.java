package edu.rims.eco_spark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "review")
@Setter
@Getter
public class Review extends Auditable {

    @Id
    @Column(name = "review_id", nullable = false, length = 255)
    private String reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;

    @Column(name = "review_rating", nullable = false)
    private Integer reviewRating;

    @Column(name = "review_description", columnDefinition = "TEXT")
    private String reviewDescription;
}
