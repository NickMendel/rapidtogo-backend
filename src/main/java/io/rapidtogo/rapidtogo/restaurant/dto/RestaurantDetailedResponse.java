package io.rapidtogo.rapidtogo.restaurant.dto;

import io.rapidtogo.rapidtogo.address.dto.AddressResponse;
import java.math.BigDecimal;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantDetailedResponse {

  private Long id;
  private String name;
  private String category;
  private String phoneNumber;
  private String website;
  private String description;
  private boolean pickUp;
  private boolean delivery;
  private Map<String, String> openingHours;
  private BigDecimal minimalOrder;
  private BigDecimal deliveryFee;
  private BigDecimal score;
  private AddressResponse address;
  private int reviewCount;
}
