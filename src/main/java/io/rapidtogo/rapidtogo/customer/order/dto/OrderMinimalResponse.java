package io.rapidtogo.rapidtogo.customer.order.dto;

import io.rapidtogo.rapidtogo.customer.order.enums.OrderStatus;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderMinimalResponse {

  private Long id;
  private String restaurantName;
  private OrderStatus status;
  private BigDecimal total;
  private int orderItemsCount;
  private boolean delivery;
  private String receivedAt;
}
