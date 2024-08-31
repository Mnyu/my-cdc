package com.example.orderservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class CreateOrderRequest {

  private long customerId;
  private BigDecimal totalValue;
  private final LocalDateTime orderDate = LocalDateTime.now();
  private final List<OrderLineDTO> lineItems = new ArrayList<>();
}
