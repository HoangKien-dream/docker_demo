package com.example.order_service.config;

import com.example.orderservice.entity.OrderDetail;
import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailEvent {
    private int productId;
    private int quantity;
    private int unitPrice;

    public OrderDetailEvent(OrderDetail orderDetail) {
        this.productId = orderDetail.getProductId();
        this.quantity = orderDetail.getQuantity();
        this.unitPrice = orderDetail.getUnitPrice();
    }
}
