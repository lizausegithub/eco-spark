package edu.rims.eco_spark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wishlist")
@Setter
@Getter
public class Wishlist extends Auditable {

    @Id
    @Column(name = "wishlist_id", nullable = false, length = 255)
    private String wishlistId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id", nullable = false)
    private Product product;
}
