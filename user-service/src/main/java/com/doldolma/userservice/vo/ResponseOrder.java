package com.doldolma.userservice.vo;

import java.util.Date;
import lombok.Data;

@Data
public class ResponseOrder {
    private String productId;
    private Integer qty;
    private Integer unitPirce;
    private Integer totalPrice;
    private Date createdAt;

    private String orderId;
}
