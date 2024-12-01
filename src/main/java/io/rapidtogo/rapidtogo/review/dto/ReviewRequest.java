package io.rapidtogo.rapidtogo.review.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {

  @NotNull
  private int qualityRate;
  private int deliveryRate;
  private String content;
}
