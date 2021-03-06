package com.doldolma.orderservice.controller;

import com.doldolma.orderservice.dto.OrderDto;
import com.doldolma.orderservice.jpa.OrderEntity;
import com.doldolma.orderservice.message.KafkaProducer;
import com.doldolma.orderservice.service.OrderService;
import com.doldolma.orderservice.vo.RequestOrder;
import com.doldolma.orderservice.vo.ResponseOrder;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/order-service")
public class OrderController {
    private final OrderService orderService;
    Environment env;
    KafkaProducer kafkaProducer;

    public OrderController(OrderService orderService, Environment env, KafkaProducer kafkaProducer) {
        this.orderService = orderService;
        this.env = env;
        this.kafkaProducer = kafkaProducer;
    }
    @GetMapping("/health_check")
    public String status() {
        return String.format("Working Good on PORT %S", env.getProperty("local.server.port"));
    }

    @PostMapping("/{userId}/orders")
    public ResponseEntity<ResponseOrder> createUser(@PathVariable("userId") String userId, @RequestBody RequestOrder order){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        log.info("Before call order data");
        // jpa
        OrderDto orderDto = mapper.map(order, OrderDto.class);
        orderDto.setUserId(userId);
        orderService.createOrder(orderDto);

        ResponseOrder responseOrder = mapper.map(orderDto, ResponseOrder.class);
        log.info("After call order data");
        // send this order to the kafka
        kafkaProducer.send("example-catalog-topic", orderDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder);
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ResponseOrder>> getOrder(@PathVariable("userId") String userId){
        log.info("Before call order data");
        Iterable<OrderEntity> orderList = orderService.getOrdersByUserId(userId);
        List<ResponseOrder> result = new ArrayList<>();
        orderList.forEach(v -> {
            result.add(new ModelMapper().map(v, ResponseOrder.class));
        });
        log.info("After call order data");
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
