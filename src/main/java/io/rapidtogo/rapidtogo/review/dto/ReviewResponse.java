package io.rapidtogo.rapidtogo.review.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

  private Long id;
  private String content;
  private int qualityRate;
  private int deliveryRate;
}
