package io.rapidtogo.rapidtogo.menu.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuCreateRequest {

  @NotBlank
  private String name;
  private String description;
}