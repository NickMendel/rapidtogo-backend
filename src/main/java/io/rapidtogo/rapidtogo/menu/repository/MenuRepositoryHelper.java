package io.rapidtogo.rapidtogo.menu.repository;

import io.rapidtogo.rapidtogo.menu.model.Menu;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuRepositoryHelper {

  private final MenuRepository menuRepository;

  /**
   * Get all menus by restaurant ID
   *
   * @param restaurantId ID of the restaurant
   * @return List of all menus
   * @throws EntityNotFoundException if no menus are found
   */
  public List<Menu> findAllByRestaurantId(Long restaurantId) {

    List<Menu> menus = menuRepository.findAllByRestaurantId(restaurantId);

    if (menus.isEmpty()) {
      throw new EntityNotFoundException("No menus found for restaurant ID " + restaurantId);
    }

    return menus;
  }

  /**
   * Get menu by restaurant ID and menu ID
   *
   * @param restaurantId ID of the restaurant
   * @param menuId       ID of the menu
   * @return Menu with the given ID
   * @throws EntityNotFoundException if no menu is found with the given ID
   */
  public Menu findByRestaurantIdAndMenuId(Long restaurantId, Long menuId) {

    return menuRepository.findByRestaurantIdAndId(restaurantId, menuId).orElseThrow(
        () -> new EntityNotFoundException(
            "Menu with ID: " + menuId + " not found for restaurant ID: " + restaurantId));
  }

  /**
   * Get menu by ID
   *
   * @param menuId ID of the menu
   * @return Menu with the given ID
   * @throws EntityNotFoundException if no menu is found with the given ID
   */
  public Menu findById(Long menuId) {

    return menuRepository.findById(menuId).orElseThrow(
        () -> new EntityNotFoundException("Menu with ID: " + menuId + " not found"));
  }

  public void checkExistence(Long menuId) {
    if (!menuRepository.existsById(menuId)) {
      throw new EntityNotFoundException("Menu with ID: " + menuId + " not found");
    }
  }
}
