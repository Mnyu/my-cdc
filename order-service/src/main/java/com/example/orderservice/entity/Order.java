package com.example.orderservice.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "purchase_order")
public class Order {

  @Id
  @SequenceGenerator(name = "order_ids", sequenceName = "seq_order", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_ids")
  private long id;

  private long customerId;

  private LocalDateTime orderDate;

  private BigDecimal totalValue;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "order")
  private List<OrderLine> lineItems;
}
