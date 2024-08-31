package com.example.orderservice.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class CreateOrderResponse {

  private final long id;
  private final long customerId;
  private final BigDecimal totalValue;
  private final LocalDateTime orderDate;
  private final List<OrderLineDTO> lineItems;
}
