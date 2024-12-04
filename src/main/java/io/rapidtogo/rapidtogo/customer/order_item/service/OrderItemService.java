package io.rapidtogo.rapidtogo.customer.order_item.service;

import io.rapidtogo.rapidtogo.customer.order_item.dto.OrderItemRequest;
import io.rapidtogo.rapidtogo.customer.order_item.mapper.OrderItemMapper;
import io.rapidtogo.rapidtogo.customer.order_item.model.OrderItem;
import io.rapidtogo.rapidtogo.partner.menu_item.model.MenuItem;
import io.rapidtogo.rapidtogo.partner.menu_item.repository.MenuItemRepositoryHelper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderItemService {

  private final MenuItemRepositoryHelper menuItemRepositoryHelper;
  private final OrderItemMapper orderItemMapper;

  @Transactional
  public List<OrderItem> createOrderItems(List<OrderItemRequest> orderItems) {

    if (orderItems == null || orderItems.isEmpty()) {
      throw new IllegalArgumentException("Order items cannot be empty");
    }

    return orderItems.stream().map(orderItemRequest -> {

      MenuItem menuItem = menuItemRepositoryHelper.findById(orderItemRequest.getMenuItemId());

      return orderItemMapper.mapToEntity(orderItemRequest, menuItem);
    }).toList();
  }

}
