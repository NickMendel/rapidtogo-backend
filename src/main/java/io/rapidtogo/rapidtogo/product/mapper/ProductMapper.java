package io.rapidtogo.rapidtogo.product.mapper;

import io.rapidtogo.rapidtogo.product.dto.ProductMinimalResponse;
import io.rapidtogo.rapidtogo.product.dto.ProductRequest;
import io.rapidtogo.rapidtogo.product.enums.Allergy;
import io.rapidtogo.rapidtogo.product.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

  public Product mapToEntity(ProductRequest request) {

    if (request == null) {
      return null;
    }

    Product product = new Product();
    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());

    if (request.getAllergies() != null && !request.getAllergies().isEmpty()) {
      product.setAllergies(
          request.getAllergies().stream().map(Allergy::valueOf).collect(Collectors.toSet()));
    }

    return product;
  }

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

  public void updateEntity(Product product, ProductRequest request) {

    if (product == null || request == null) {
      return;
    }

    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setPrice(request.getPrice());

    if (request.getAllergies() != null && !request.getAllergies().isEmpty()) {
      product.setAllergies(
          request.getAllergies().stream().map(Allergy::valueOf).collect(Collectors.toSet()));
    }
  }
}
