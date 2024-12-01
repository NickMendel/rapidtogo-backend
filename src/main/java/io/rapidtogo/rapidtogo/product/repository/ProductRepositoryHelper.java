package io.rapidtogo.rapidtogo.product.repository;

import io.rapidtogo.rapidtogo.exception.UniqueNameException;
import io.rapidtogo.rapidtogo.product.model.Product;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductRepositoryHelper {

  private final ProductRepository productRepository;

  /**
   * Check if a product with the given name already exists in the menu with the given ID
   *
   * @param name   Name of the product
   * @param menuId ID of the menu
   * @throws UniqueNameException if a product with the given name already exists in the menu
   */
  public void checkExistenceByNameAndMenuId(String name, Long menuId) {

    if (productRepository.existsByNameAndMenuId(name, menuId)) {
      throw new UniqueNameException(
          "Product with name: " + name + " already exists in menu with ID: " + menuId);
    }
  }

  /**
   * Find a product by its ID and the ID of the menu it belongs to
   *
   * @param productId ID of the product
   * @param menuId    ID of the menu
   * @return Product with the given ID and menu ID
   * @throws EntityNotFoundException if the product with the given ID does not exist in the menu
   *                                 with the given ID
   */
  public Product findByIdAndMenuId(Long productId, Long menuId) {

    return productRepository.findByIdAndMenuId(productId, menuId)
        .orElseThrow(() -> new EntityNotFoundException(
            "Product with ID: " + productId + " not found in menu with ID: " + menuId));
  }

}
