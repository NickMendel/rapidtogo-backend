package io.rapidtogo.rapidtogo.customer.order_item.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {

  @NotNull(message = "Menu item id is required")
  private Long menuItemId;

  @Positive(message = "Quantity must be greater than 0")
  private int quantity;

  @Size(max = 1000, message = "Notes must be less than 1000 characters")
  private String notes;

}
