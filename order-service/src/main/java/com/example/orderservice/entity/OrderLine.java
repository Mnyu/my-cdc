package com.example.orderservice.entity;

import com.example.orderservice.common.OrderLineStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "order_lines")
public class OrderLine {

  @Id
  @SequenceGenerator(name = "order_line_ids", sequenceName = "seq_order_line", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_line_ids")
  private long id;

  private String item;

  private int quantity;

  private BigDecimal totalPrice;

  @Enumerated(EnumType.STRING)
  private OrderLineStatus status;

  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

}
