package com.example.invoiceservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Configuration
@Slf4j
public class KafkaConfig {

  @KafkaListener(topics = "invoices", groupId="invoice-group-1")
  public void consume(String value) {
    log.info("******************");
    log.info(value);
    log.info("******************");
  }
}
