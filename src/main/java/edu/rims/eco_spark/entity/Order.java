package edu.rims.eco_spark.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Generated;

import edu.rims.eco_spark.constant.OrderStatus;

@Entity
@Table(name = "product_order")
@Setter
@Getter
public class Order extends Auditable {

    @Id
    @Column(name = "product_order_id", nullable = false, length = 255)
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "product_order_total_price", nullable = false)
    private Double orderTotalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_order_status")
    private OrderStatus orderStatus = OrderStatus.CART;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order")
    private List<Payment> payments;

    public boolean addOrderItem(OrderItem orderItem) {
        if (orderItems == null) {
            orderItems = new ArrayList<>();
        }

        if (itemExists(orderItem.getProduct().getProductId())) {
            return false;
        }

        orderItem.setOrder(this);
        orderItems.add(orderItem);
        updateDetails();
        return true;
    }

    private void updateDetails() {
        int totalQuantity = 0;
        double totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalQuantity += orderItem.getOrderItemQuantity();
            totalPrice += orderItem.getOrderItemPrice();
        }
        this.orderTotalPrice = totalPrice;
        // orderQuantity = totalQuantity;
    }

    private boolean itemExists(String itemId) {
        for (OrderItem orderItem : orderItems) {
            if (itemId.equals(orderItem.getProduct().getProductId())) {
                return true;
            }
        }
        return false;
    }

    public void removeOrderItem(String ordertItemId) {
        for (OrderItem orderItem : orderItems) {
            if (orderItem.getOrderItemId().equals(ordertItemId)) {
                orderItem.setOrder(null);
                orderItems.remove(orderItem);
                break;
            }
        }
        updateDetails();
    }
}