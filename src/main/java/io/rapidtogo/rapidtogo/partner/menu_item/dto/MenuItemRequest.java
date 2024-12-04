package io.rapidtogo.rapidtogo.partner.menu_item.dto;

import io.rapidtogo.rapidtogo.partner.menu_item.enums.Allergy;
import io.rapidtogo.rapidtogo.utils.validation.enum_validation.ValidEnum;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemRequest {

  @NotBlank(message = "Menu Item Name is required.")
  @Size(max = 100, message = "Menu Item Name must be less than 100 characters.")
  private String name;

  @NotBlank(message = "Menu Item Description is required.")
  @Size(max = 1000, message = "Menu Item Description must be less than 1000 characters.")
  private String description;

  @NotNull(message = "Menu Item Price is required.")
  @Positive(message = "Menu Item Price must be positive.")
  private BigDecimal price;

  @NotNull(message = "Menu Item Active status is required.")
  private boolean active;

  @Valid
  private Set<@ValidEnum(enumClass = Allergy.class) String> allergies;
}
