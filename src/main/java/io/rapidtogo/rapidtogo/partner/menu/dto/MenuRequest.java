package io.rapidtogo.rapidtogo.partner.menu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuRequest {

  @NotBlank(message = "Menu name is required.")
  private String name;
  private String description;

  @NotNull(message = "Menu active status is required.")
  private boolean active;
}