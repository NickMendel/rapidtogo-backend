package io.rapidtogo.rapidtogo.restaurant.dto;

import io.rapidtogo.rapidtogo.restaurant.enums.Category;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantMinimalResponse {

  private Long id;
  private String name;
  private Category category;
  private BigDecimal deliveryFee;
  private BigDecimal score;
  private BigDecimal minimalOrder;
  private int reviewCount;
}