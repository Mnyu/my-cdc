package com.example.orderservice.event;

import com.example.orderservice.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class InvoiceCreatedEvent implements OutboxEvent {

  private static final ObjectMapper mapper = new ObjectMapper();

  private final long customerId;
  private final JsonNode payload;
  private final Instant timestamp;

  public static InvoiceCreatedEvent of(Order order) {
    ObjectNode objectNode = mapper.createObjectNode()
        .put("orderId", order.getId())
        .put("invoiceDate", order.getOrderDate().toString())
        .put("invoiceValue", order.getTotalValue());

    return new InvoiceCreatedEvent(order.getCustomerId(), objectNode, Instant.now());
  }

  @Override
  public String getAggregateId() {
    return String.valueOf(customerId);
  }

  @Override
  public String getAggregateType() {
    return "Customer";
  }

  @Override
  public String getType() {
    return "InvoiceCreated";
  }

  @Override
  public Instant getTimestamp() {
    return timestamp;
  }

  @Override
  public JsonNode getPayload() {
    return payload;
  }
}
