package edu.rims.eco_spark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import edu.rims.eco_spark.constant.OrderItemStatus;

@Entity
@Table(name = "order_item")
@Setter
@Getter
public class OrderItem extends Auditable {

    
    @Id
    @Column(name = "order_item_id", nullable = false, length = 255)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderItemId;
    
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    
    @Column(name = "order_item_quantity", nullable = false)
    private Integer orderItemQuantity;
    
    @Column(name = "order_item_price", nullable = false)
    private Double orderItemPrice;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "order_item_status")
    private OrderItemStatus orderItemStatus = OrderItemStatus.ADDED;

    public OrderItem() {

    }

    public OrderItem(Product product) {
        this.product = product;
        orderItemQuantity = 1;
        orderItemPrice = product.getProductPrice();
    }

    public void incrementQuantity() {
        orderItemQuantity += 1;
        orderItemPrice = product.getProductPrice() * orderItemQuantity;
    }

    public void decrementQuantity() {
        orderItemQuantity -= 1;
        orderItemPrice = product.getProductPrice() * orderItemQuantity;
    }
}
