package com.doldolma.orderservice.service;

import com.doldolma.orderservice.dto.OrderDto;
import com.doldolma.orderservice.jpa.OrderEntity;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
