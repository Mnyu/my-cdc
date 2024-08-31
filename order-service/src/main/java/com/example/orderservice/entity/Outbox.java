package com.example.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "outbox_events")
public class Outbox {

  @Id
  private UUID id;

  @Column(nullable = false)
  private String aggregateType;

  @Column(nullable = false)
  private String aggregateId;

  @Column(nullable = false)
  private String type;

  @Column(nullable = false)
  private String payload;

  @Column(updatable = false)
  private Instant timestamp;
}
