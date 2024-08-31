package com.example.orderservice.service;

import com.example.orderservice.entity.Outbox;
import com.example.orderservice.event.OutboxEvent;
import com.example.orderservice.repository.OutboxRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OutboxService {

  private static final ObjectMapper mapper = new ObjectMapper();

  private final OutboxRepository outboxRepository;

  public void save(OutboxEvent outboxEvent) {
    try {
    Outbox outbox = Outbox.builder()
        .id(UUID.randomUUID())
        .aggregateId(outboxEvent.getAggregateId())
        .aggregateType(outboxEvent.getAggregateType())
        .type(outboxEvent.getType())
        .timestamp(outboxEvent.getTimestamp())
        .payload(mapper.writeValueAsString(outboxEvent.getPayload()))
        .build();
      outboxRepository.save(outbox);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
