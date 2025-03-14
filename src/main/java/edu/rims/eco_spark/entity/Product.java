package edu.rims.eco_spark.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Setter
@Getter
public class Product extends Auditable {

    @Id
    @Column(name = "product_id", nullable = false, length = 255)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String productId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = true)
    private Category category;

    @Column(name = "product_title", nullable = false, length = 50)
    private String productTitle;

    @Column(name = "product_description", columnDefinition = "TEXT")
    private String productDescription;

    @Column(name = "product_image_url", columnDefinition = "TEXT")
    private String productImageUrl;

    @Column(name = "product_price", nullable = false)
    private Double productPrice;

    @Column(name = "product_stock", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer productStock = 0;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orders;

    @OneToMany(mappedBy = "product")
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ManyToMany(mappedBy = "products")
    private List<Widget> widgets;

    public void addWidget(Widget widget) {
        if (widgets == null)
            widgets = new ArrayList<>();

        widgets.add(widget);
    }

    public void removeWidget(Widget widget) {
        widgets.remove(widget);
    }
}