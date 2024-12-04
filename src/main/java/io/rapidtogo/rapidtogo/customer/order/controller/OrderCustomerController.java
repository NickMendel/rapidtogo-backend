package io.rapidtogo.rapidtogo.customer.order.controller;

import io.rapidtogo.rapidtogo.customer.order.dto.OrderRequest;
import io.rapidtogo.rapidtogo.customer.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customers/orders")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class OrderCustomerController {

  private final OrderService orderService;

  @PostMapping
  public ResponseEntity<String> createOrder(@RequestParam Long restaurantId,
      @RequestBody OrderRequest request) {

    String successMessage = orderService.createOrder(restaurantId, request);
    log.info(successMessage);

    return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
  }

}
