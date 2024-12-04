package io.rapidtogo.rapidtogo.customer.order.service;

import io.rapidtogo.rapidtogo.customer.order.dto.OrderRequest;
import io.rapidtogo.rapidtogo.customer.order.mapper.OrderMapper;
import io.rapidtogo.rapidtogo.customer.order.model.Order;
import io.rapidtogo.rapidtogo.customer.order.repository.OrderRepository;
import io.rapidtogo.rapidtogo.customer.order_item.model.OrderItem;
import io.rapidtogo.rapidtogo.customer.order_item.service.OrderItemService;
import io.rapidtogo.rapidtogo.partner.restaurant.model.Restaurant;
import io.rapidtogo.rapidtogo.partner.restaurant.repository.RestaurantRepositoryHelper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;
  private final RestaurantRepositoryHelper restaurantRepositoryHelper;
  private final OrderMapper orderMapper;
  private final OrderItemService orderItemService;

  @Transactional
  public String createOrder(Long restaurantId, OrderRequest request) {

    Restaurant restaurant = restaurantRepositoryHelper.findById(restaurantId);
    List<OrderItem> orderItems = orderItemService.createOrderItems(request.getOrderItems());
    Order order = orderMapper.mapToEntity(request, orderItems, restaurant);
    order = orderRepository.save(order);

    return "Order created successfully with ID: " + order.getId() + " for restaurant ID: "
        + restaurantId;
  }
}
