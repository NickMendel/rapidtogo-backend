package io.rapidtogo.rapidtogo.product.mapper;

import io.rapidtogo.rapidtogo.product.dto.ProductMinimalResponse;
import io.rapidtogo.rapidtogo.product.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public ProductMinimalResponse mapToMinimalDto(Product product) {

    if (product == null) {
      return null;
    }

    ProductMinimalResponse productMinimalResponse = new ProductMinimalResponse();
    productMinimalResponse.setId(product.getId());
    productMinimalResponse.setName(product.getName());
    productMinimalResponse.setPrice(product.getPrice());

    return productMinimalResponse;
  }

  public List<ProductMinimalResponse> mapToListMinimalDto(List<Product> products) {

    if (products == null || products.isEmpty()) {
      return new ArrayList<>();
    }

    return products.stream().map(this::mapToMinimalDto).toList();
  }
}
