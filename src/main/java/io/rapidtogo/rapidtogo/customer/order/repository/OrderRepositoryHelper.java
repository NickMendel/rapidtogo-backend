package io.rapidtogo.rapidtogo.customer.order.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderRepositoryHelper {

  private final OrderRepository orderRepository;

}
