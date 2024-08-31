package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderLine;
import com.example.orderservice.event.InvoiceCreatedEvent;
import com.example.orderservice.event.OrderCreatedEvent;
import com.example.orderservice.model.CreateOrderRequest;
import com.example.orderservice.model.CreateOrderResponse;
import com.example.orderservice.model.OrderLineDTO;
import com.example.orderservice.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

  private final OrderRepository orderRepository;
  private final OutboxService outboxService;

  @Transactional
  public CreateOrderResponse bookOrder(CreateOrderRequest createOrderRequest) {
    Order order = buildOrder(createOrderRequest);
    order = orderRepository.save(order);

    final OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.of(order);
    outboxService.save(orderCreatedEvent);

    final InvoiceCreatedEvent invoiceCreatedEvent = InvoiceCreatedEvent.of(order);
    outboxService.save(invoiceCreatedEvent);

    return buildCreateOrderResponse(order);
  }

  private Order buildOrder(CreateOrderRequest createOrderRequest) {
    List<OrderLine> orderLines = createOrderRequest.getLineItems().stream().map(this::buildOrderLine).toList();
    return Order.builder()
        .customerId(createOrderRequest.getCustomerId())
        .orderDate(createOrderRequest.getOrderDate())
        .totalValue(createOrderRequest.getTotalValue())
        .lineItems(orderLines)
        .build();
  }

  private OrderLine buildOrderLine(OrderLineDTO orderLineDTO) {
    return OrderLine.builder()
        .item(orderLineDTO.getItem())
        .quantity(orderLineDTO.getQuantity())
        .totalPrice(orderLineDTO.getTotalPrice())
        .status(orderLineDTO.getStatus())
        .build();
  }

  private CreateOrderResponse buildCreateOrderResponse(Order order) {
    List<OrderLineDTO> orderLineDTOs = order.getLineItems().stream().map(this::buildOrderLineDTO).toList();
    return CreateOrderResponse.builder()
        .id(order.getId())
        .customerId(order.getCustomerId())
        .totalValue(order.getTotalValue())
        .orderDate(order.getOrderDate())
        .lineItems(orderLineDTOs)
        .build();
  }

  private OrderLineDTO buildOrderLineDTO(OrderLine orderLine) {
    return OrderLineDTO.builder()
        .id(orderLine.getId())
        .item(orderLine.getItem())
        .quantity(orderLine.getQuantity())
        .totalPrice(orderLine.getTotalPrice())
        .status(orderLine.getStatus())
        .build();
  }
}
