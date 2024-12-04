package io.rapidtogo.rapidtogo.partner.menu_item.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemMinimalResponse {

  private Long id;
  private String name;
  private String description;
  private BigDecimal price;
}
