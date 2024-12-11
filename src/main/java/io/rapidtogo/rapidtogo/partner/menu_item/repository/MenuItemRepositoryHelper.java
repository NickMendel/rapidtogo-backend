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
   * Check if a menu item with the given name already exists in the menu with the given ID and the
   * ID of the user who owns the menu
   *
   * @param name   Name of the menu item
   * @param menuId ID of the menu
   * @throws UniqueNameException if a menu item with the given name already exists in the menu
   */
  public void checkExistenceByNameAndMenuId(String name, Long menuId, String userId) {

    if (menuItemRepository.existsByNameAndMenuId(name, menuId, userId)) {
      throw new UniqueNameException(
          "MenuItem with name: " + name + " already exists in menu with ID: " + menuId);
    }
  }

  /**
   * Find a menu item by its ID and the ID of the menu it belongs to and the ID of the user who owns
   * the menu
   *
   * @param menuItemId ID of the product
   * @param menuId     ID of the menu
   * @return MenuItem with the given ID and menu ID
   * @throws EntityNotFoundException if the product with the given ID does not exist in the menu
   *                                 with the given ID
   */
  public MenuItem findByIdAndMenuId(Long menuItemId, Long menuId) {

    return menuItemRepository.findByIdAndMenuId(menuItemId, menuId)
        .orElseThrow(() -> new EntityNotFoundException(
            "MenuItem with ID: " + menuItemId + " not found in menu with ID: " + menuId));
  }

  /**
   * Find a menu item by its ID and the ID of the menu it belongs to and the ID of the user who owns
   * the menu
   *
   * @param menuItemId ID of the product
   * @param menuId     ID of the menu
   * @param userId     ID of the user who owns the menu
   * @return MenuItem with the given ID and menu ID
   * @throws EntityNotFoundException if the product with the given ID does not exist in the menu
   *                                 with the given ID
   */
  public MenuItem findByIdAndMenuId(Long menuItemId, Long menuId, String userId) {

    return menuItemRepository.findByIdAndMenuId(menuItemId, menuId, userId)
        .orElseThrow(() -> new EntityNotFoundException(
            "MenuItem with ID: " + menuItemId + " not found in menu with ID: " + menuId));
  }

  /**
   * Find a menu item by given ID
   *
   * @param menuItemId ID of the menu item
   * @return MenuItem with the given ID
   * @throws EntityNotFoundException If the menu item with the given ID does not exist
   */
  public MenuItem findById(Long menuItemId) {

    return menuItemRepository.findById(menuItemId)
        .orElseThrow(() -> new EntityNotFoundException(
            "MenuItem with ID: " + menuItemId + " not found"));
  }

  /**
   * Find a menu item by given ID and the ID of the user who owns the menu item
   *
   * @param menuItemId ID of the menu item
   * @param userId     ID of the user who owns the menu item
   * @return MenuItem with the given ID
   * @throws EntityNotFoundException If the menu item with the given ID does not exist
   */
  public MenuItem findById(Long menuItemId, String userId) {

    return menuItemRepository.findById(menuItemId, userId)
        .orElseThrow(() -> new EntityNotFoundException(
            "MenuItem with ID: " + menuItemId + " not found"));
  }

}
