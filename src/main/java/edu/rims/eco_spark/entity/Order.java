package edu.rims.eco_spark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import edu.rims.eco_spark.constant.OrderStatus;

@Entity
@Table(name = "product_order")
@Setter
@Getter
public class Order extends Auditable {

    @Id
    @Column(name = "product_order_id", nullable = false, length = 255)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "product_order_total_price", nullable = false)
    private Double orderTotalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_order_status")
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order")
    private List<Payment> payments;

    public void addOrderItem(OrderItem orderItem) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOrderItem'");
    }

    public void removeOrderItem(String orderItemId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeOrderItem'");
    }
}