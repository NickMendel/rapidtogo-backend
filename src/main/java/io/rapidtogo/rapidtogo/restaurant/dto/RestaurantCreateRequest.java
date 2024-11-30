package io.rapidtogo.rapidtogo.restaurant.dto;

import io.rapidtogo.rapidtogo.address.dto.AddressCreateRequest;
import io.rapidtogo.rapidtogo.restaurant.enums.Category;
import io.rapidtogo.rapidtogo.utils.validation.enum_validation.ValidEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantCreateRequest {

  @NotBlank(message = "Name of the restaurant is required.")
  private String name;

  @NotNull(message = "Opening hours of the restaurant is required.")
  private Map<String, String> openingHours;

  @NotNull(message = "Pick up option is required.")
  private boolean pickUp;

  @NotNull(message = "Delivery option is required.")
  private boolean delivery;

  @NotNull(message = "Categories of the restaurant are required.")
  private Set<@ValidEnum(enumClass = Category.class) String> category;

  @PositiveOrZero(message = "Minimal order must be positive or zero.")
  private BigDecimal minimalOrder;

  @Valid
  @NotNull(message = "Address of the restaurant is required.")
  private AddressCreateRequest address;

  @PositiveOrZero(message = "Delivery fee must be positive or zero.")
  private BigDecimal deliveryFee;
  private String phoneNumber;
  private String website;
  private String description;
}
