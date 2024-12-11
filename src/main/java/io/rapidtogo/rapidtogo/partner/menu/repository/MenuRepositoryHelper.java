package io.rapidtogo.rapidtogo.partner.menu.repository;

import io.rapidtogo.rapidtogo.partner.menu.model.Menu;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuRepositoryHelper {

  private final MenuRepository menuRepository;

  /**
   * Get all menus by restaurant ID owned by a user
   *
   * @param restaurantId ID of the restaurant
   * @param userId       ID of the user who owns the restaurant
   * @return List of all menus
   * @throws EntityNotFoundException if no menus are found
   */
  public List<Menu> findAllByRestaurantId(Long restaurantId, String userId) {

    List<Menu> menus = menuRepository.findAllByRestaurantId(restaurantId, userId);

    if (menus.isEmpty()) {
      throw new EntityNotFoundException("No menus found for restaurant ID " + restaurantId);
    }

    return menus;
  }

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
  public Menu findByRestaurantIdAndById(Long restaurantId, Long menuId) {

    return menuRepository.findByRestaurantIdAndById(restaurantId, menuId).orElseThrow(
        () -> new EntityNotFoundException(
            "Menu with ID: " + menuId + " not found for restaurant ID: " + restaurantId));
  }

  /**
   * Get menu by restaurant ID and menu ID
   *
   * @param restaurantId ID of the restaurant
   * @param menuId       ID of the menu
   * @return Menu with the given ID
   * @throws EntityNotFoundException if no menu is found with the given ID
   */
  public Menu findByRestaurantIdAndById(Long restaurantId, Long menuId, String userId) {

    return menuRepository.findByRestaurantIdAndById(restaurantId, menuId, userId).orElseThrow(
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

  /**
   * Get menu by ID owned by a user
   *
   * @param menuId ID of the menu
   * @param userId ID of the user who owns the menu
   * @return Menu with the given ID
   * @throws EntityNotFoundException if no menu is found with the given ID
   */
  public Menu findById(Long menuId, String userId) {

    return menuRepository.findById(menuId, userId).orElseThrow(
        () -> new EntityNotFoundException("Menu with ID: " + menuId + " not found"));
  }

  /**
   * Check if menu exists
   *
   * @param menuId ID of the menu
   * @throws EntityNotFoundException if no menu is found with the given ID
   */
  public void checkExistence(Long menuId) {

    if (!menuRepository.existsById(menuId)) {
      throw new EntityNotFoundException("Menu with ID: " + menuId + " not found");
    }
  }

  /**
   * Check if menu exists owned by a user
   *
   * @param menuId ID of the menu
   * @param userId ID of the user who owns the menu
   * @throws EntityNotFoundException if no menu is found with the given ID
   */
  public void checkExistence(Long menuId, String userId) {

    if (!menuRepository.existsById(menuId, userId)) {
      throw new EntityNotFoundException("Menu with ID: " + menuId + " not found");
    }
  }
}
