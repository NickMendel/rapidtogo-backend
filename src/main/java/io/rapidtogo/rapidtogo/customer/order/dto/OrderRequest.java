package io.rapidtogo.rapidtogo.customer.order.dto;

import io.rapidtogo.rapidtogo.address.dto.AddressRequest;
import io.rapidtogo.rapidtogo.customer.order_item.dto.OrderItemRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

  @NotNull(message = "Order items are required.")
  private List<@Valid OrderItemRequest> orderItems;
  private AddressRequest deliveryAddress;

  @NotNull(message = "Delivery status is required.")
  private boolean delivery;

  @Size(max = 1000, message = "Notes must be less than 1000 characters.")
  private String notes;
}
