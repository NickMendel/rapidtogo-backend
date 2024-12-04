package io.rapidtogo.rapidtogo.customer.order_item.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemResponse {

  private Long id;
  private String name;
  private BigDecimal price;
  private int quantity;
  private BigDecimal totalPrice;
}
