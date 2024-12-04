package io.rapidtogo.rapidtogo.partner.restaurant.dto;

import io.rapidtogo.rapidtogo.partner.restaurant.enums.Category;
import java.math.BigDecimal;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantMinimalResponse {

  private Long id;
  private String name;
  private Set<Category> category;
  private BigDecimal deliveryFee;
  private Double averageRating;
  private BigDecimal minimalOrder;
  private Integer totalReviews;
}
