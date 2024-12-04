package io.rapidtogo.rapidtogo.customer.order.mapper;

import io.rapidtogo.rapidtogo.address.mapper.AddressMapper;
import io.rapidtogo.rapidtogo.customer.order.dto.OrderMinimalResponse;
import io.rapidtogo.rapidtogo.customer.order.dto.OrderRequest;
import io.rapidtogo.rapidtogo.customer.order.model.Order;
import io.rapidtogo.rapidtogo.customer.order_item.model.OrderItem;
import io.rapidtogo.rapidtogo.partner.restaurant.model.Restaurant;
import io.rapidtogo.rapidtogo.utils.Date_Time.LocalDateTimeFormatter;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

  private final AddressMapper addressMapper;
  private final LocalDateTimeFormatter localDateTimeFormatter;

  public Order mapToEntity(OrderRequest request, List<OrderItem> orderItems,
      Restaurant restaurant) {

    if (request == null || orderItems == null || orderItems.isEmpty()) {
      return null;
    }

    Order order = new Order();
    order.setOrderItems(orderItems);
    order.setTotal(
        orderItems.stream().map(OrderItem::getTotal).reduce(BigDecimal.ZERO, BigDecimal::add));
    order.setRestaurant(restaurant);

    if (request.getNotes() != null) {
      order.setNotes(request.getNotes());
    }

    if (request.isDelivery()) {
      if (request.getDeliveryAddress() == null) {
        throw new IllegalArgumentException("Delivery address is required for delivery orders");
      }

      order.setDeliveryAddress(addressMapper.mapToEntity(request.getDeliveryAddress()));
      order.setDelivery(true);
      order.setDeliveryFee(restaurant.getDeliveryFee());
    } else {
      order.setTakeAway(true);
    }

    return order;
  }

  public OrderMinimalResponse mapToMinimalDto(Order order) {

    if (order == null) {
      return null;
    }

    OrderMinimalResponse orderMinimalResponse = new OrderMinimalResponse();
    orderMinimalResponse.setId(order.getId());
    orderMinimalResponse.setRestaurantName(order.getRestaurant().getName());
    orderMinimalResponse.setOrderItemsCount(order.getOrderItems().size());
    orderMinimalResponse.setTotal(order.getTotal());
    orderMinimalResponse.setDelivery(order.isDelivery());
    orderMinimalResponse.setReceivedAt(localDateTimeFormatter.formatToIso(order.getReceivedAt()));

    return orderMinimalResponse;
  }
}
