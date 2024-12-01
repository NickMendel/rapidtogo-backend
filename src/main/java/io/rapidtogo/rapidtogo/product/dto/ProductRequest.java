package io.rapidtogo.rapidtogo.product.dto;

import io.rapidtogo.rapidtogo.product.enums.Allergy;
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
public class ProductRequest {

  @NotBlank
  @Size(max = 100)
  private String name;

  @NotBlank
  @Size(max = 1000)
  private String description;

  @NotNull
  @Positive
  private BigDecimal price;

  @Valid
  private Set<@ValidEnum(enumClass = Allergy.class) String> allergies;
}
