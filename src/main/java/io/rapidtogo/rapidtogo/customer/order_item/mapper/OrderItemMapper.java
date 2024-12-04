package io.rapidtogo.rapidtogo.customer.order_item.mapper;

import io.rapidtogo.rapidtogo.customer.order_item.dto.OrderItemRequest;
import io.rapidtogo.rapidtogo.customer.order_item.model.OrderItem;
import io.rapidtogo.rapidtogo.partner.menu_item.model.MenuItem;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

  public OrderItem mapToEntity(OrderItemRequest request, MenuItem menuItem) {

    if (request == null) {
      return null;
    }

    OrderItem orderItem = new OrderItem();
    orderItem.setName(menuItem.getName());
    orderItem.setPrice(menuItem.getPrice());
    orderItem.setQuantity(request.getQuantity());
    orderItem.setTotal(menuItem.getPrice().multiply(new BigDecimal(request.getQuantity())));
    orderItem.setMenuItem(menuItem);

    if (request.getNotes() != null) {
      orderItem.setNotes(request.getNotes());
    }

    return orderItem;
  }
}
