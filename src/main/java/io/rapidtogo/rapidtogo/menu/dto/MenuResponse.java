package io.rapidtogo.rapidtogo.menu.dto;

import io.rapidtogo.rapidtogo.product.dto.ProductMinimalResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {

  private Long id;
  private String name;
  private String description;
  private List<ProductMinimalResponse> products;
}
