package com.example.orderservice.controller;

import com.example.orderservice.model.CreateOrderRequest;
import com.example.orderservice.model.CreateOrderResponse;
import com.example.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<CreateOrderResponse> bookOrder(@RequestBody CreateOrderRequest request) {
    CreateOrderResponse createOrderResponse = orderService.bookOrder(request);
    return ResponseEntity.status(HttpStatus.OK).body(createOrderResponse);
  }

}
