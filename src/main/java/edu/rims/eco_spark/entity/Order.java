package edu.rims.eco_spark.entity;

import edu.rims.eco_spark.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "product_order")
@Setter
@Getter
public class Order extends Auditable {

    @Id
    @Column(name = "order_id", nullable = false, length = 255)
    private String orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "order_total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal orderTotalPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus = OrderStatus.PENDING;
}
