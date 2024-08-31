package com.example.orderservice.model;

import com.example.orderservice.common.OrderLineStatus;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineDTO {
  private Long id;
  private String item;
  private int quantity;
  private BigDecimal totalPrice;
  private OrderLineStatus status = OrderLineStatus.ENTERED;

}
