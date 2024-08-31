package com.example.orderservice.event;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderLine;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreatedEvent implements OutboxEvent {

  private static final ObjectMapper mapper = new ObjectMapper();

  private final long id;
  private final JsonNode payload;
  private final Instant timestamp;

  public static OrderCreatedEvent of(Order order) {
    ObjectNode objectNode = mapper.createObjectNode()
        .put("id", order.getId())
        .put("customerId", order.getCustomerId())
        .put("orderDate", order.getOrderDate().toString());

    ArrayNode lineItemsArrayNode = objectNode.putArray("lineItems");

    for (OrderLine orderLine : order.getLineItems()) {
      ObjectNode lineObjectNode = mapper.createObjectNode()
          .put("id", orderLine.getId())
          .put("item", orderLine.getItem())
          .put("quantity", orderLine.getQuantity())
          .put("totalPrice", orderLine.getTotalPrice())
          .put("status", orderLine.getStatus().name());
      lineItemsArrayNode.add(lineObjectNode);
    }
    return new OrderCreatedEvent(order.getId(), objectNode, Instant.now());
  }

  @Override
  public String getAggregateId() {
    return String.valueOf(id);
  }

  @Override
  public String getAggregateType() {
    return "Order";
  }

  @Override
  public String getType() {
    return "OrderCreated";
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
