package io.rapidtogo.rapidtogo.menu.service;

import io.rapidtogo.rapidtogo.menu.dto.MenuRequest;
import io.rapidtogo.rapidtogo.menu.dto.MenuResponse;
import io.rapidtogo.rapidtogo.menu.mapper.MenuMapper;
import io.rapidtogo.rapidtogo.menu.model.Menu;
import io.rapidtogo.rapidtogo.menu.repository.MenuRepository;
import io.rapidtogo.rapidtogo.menu.repository.MenuRepositoryHelper;
import io.rapidtogo.rapidtogo.restaurant.model.Restaurant;
import io.rapidtogo.rapidtogo.restaurant.repository.RestaurantRepositoryHelper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuService {

  private final MenuRepositoryHelper menuRepositoryHelper;
  private final RestaurantRepositoryHelper restaurantRepositoryHelper;
  private final MenuRepository menuRepository;
  private final MenuMapper menuMapper;

  @Transactional
  public String createMenu(Long restaurantId, MenuRequest request) {

    Restaurant restaurant = restaurantRepositoryHelper.findById(restaurantId);
    Menu menu = menuMapper.mapToEntity(request);
    restaurant.getMenus().add(menu);
    menu.setRestaurant(restaurant);

    return "Menu for restaurant " + restaurant.getName() + " created successfully";
  }

  @Transactional(readOnly = true)
  public List<MenuResponse> getAllMenusByRestaurantId(Long restaurantId) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    List<Menu> menus = menuRepositoryHelper.findAllByRestaurantId(restaurantId);

    return menuMapper.mapToListDto(menus);
  }

  @Transactional(readOnly = true)
  public MenuResponse getMenuByRestaurantIdAndById(Long restaurantId, Long menuId) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    Menu menu = menuRepositoryHelper.findByRestaurantIdAndById(restaurantId, menuId);

    return menuMapper.mapToDto(menu);
  }

  @Transactional
  public String updateMenu(Long restaurantId, Long menuId, MenuRequest request) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    Menu menu = menuRepositoryHelper.findByRestaurantIdAndById(restaurantId, menuId);
    menuMapper.updateEntity(menu, request);

    return "Menu updated successfully";
  }

  @Transactional
  public String deleteMenu(Long restaurantId, Long menuId) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    Menu menu = menuRepositoryHelper.findByRestaurantIdAndById(restaurantId, menuId);
    menuRepository.delete(menu);

    return "Menu deleted successfully";
  }

}
