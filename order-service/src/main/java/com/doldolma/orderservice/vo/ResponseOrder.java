package com.doldolma.orderservice.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Date;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class ResponseOrder {
    private String productId;
    private String qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
}
