package com.example.orderservice.event;

import com.fasterxml.jackson.databind.JsonNode;
import java.time.Instant;

public interface OutboxEvent {

  String getAggregateId();

  String getAggregateType();

  String getType();

  Instant getTimestamp();

  JsonNode getPayload();

}
