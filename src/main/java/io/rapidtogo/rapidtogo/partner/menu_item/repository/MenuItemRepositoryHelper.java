package io.rapidtogo.rapidtogo.partner.menu_item.repository;

import io.rapidtogo.rapidtogo.exception.UniqueNameException;
import io.rapidtogo.rapidtogo.partner.menu_item.model.MenuItem;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemRepositoryHelper {

  private final MenuItemRepository menuItemRepository;

  /**
   * Check if a product with the given name already exists in the menu with the given ID
   *
   * @param name   Name of the product
   * @param menuId ID of the menu
   * @throws UniqueNameException if a product with the given name already exists in the menu
   */
  public void checkExistenceByNameAndMenuId(String name, Long menuId) {

    if (menuItemRepository.existsByNameAndMenuId(name, menuId)) {
      throw new UniqueNameException(
          "MenuItem with name: " + name + " already exists in menu with ID: " + menuId);
    }
  }

  /**
   * Find a product by its ID and the ID of the menu it belongs to
   *
   * @param productId ID of the product
   * @param menuId    ID of the menu
   * @return MenuItem with the given ID and menu ID
   * @throws EntityNotFoundException if the product with the given ID does not exist in the menu
   *                                 with the given ID
   */
  public MenuItem findByIdAndMenuId(Long productId, Long menuId) {

    return menuItemRepository.findByIdAndMenuId(productId, menuId)
        .orElseThrow(() -> new EntityNotFoundException(
            "MenuItem with ID: " + productId + " not found in menu with ID: " + menuId));
  }

}
