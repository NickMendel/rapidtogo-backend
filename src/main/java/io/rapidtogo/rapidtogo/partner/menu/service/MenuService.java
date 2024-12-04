package io.rapidtogo.rapidtogo.partner.menu.service;

import io.rapidtogo.rapidtogo.partner.menu.dto.MenuRequest;
import io.rapidtogo.rapidtogo.partner.menu.dto.MenuResponse;
import io.rapidtogo.rapidtogo.partner.menu.mapper.MenuMapper;
import io.rapidtogo.rapidtogo.partner.menu.model.Menu;
import io.rapidtogo.rapidtogo.partner.menu.repository.MenuRepository;
import io.rapidtogo.rapidtogo.partner.menu.repository.MenuRepositoryHelper;
import io.rapidtogo.rapidtogo.partner.restaurant.model.Restaurant;
import io.rapidtogo.rapidtogo.partner.restaurant.repository.RestaurantRepositoryHelper;
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

    return "Menu for restaurant ID " + restaurantId + " created successfully";
  }

  @Transactional(readOnly = true)
  public List<MenuResponse> getAllMenusByRestaurantId(Long restaurantId) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    List<Menu> menus = menuRepositoryHelper.findAllByRestaurantId(restaurantId);

    return menuMapper.mapToListDto(menus);
  }

  @Transactional(readOnly = true)
  public MenuResponse getMenuByIdAndByRestaurantId(Long menuId, Long restaurantId) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    Menu menu = menuRepositoryHelper.findByRestaurantIdAndById(restaurantId, menuId);

    return menuMapper.mapToDto(menu);
  }

  @Transactional
  public String updateMenu(Long restaurantId, Long menuId, MenuRequest request) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    Menu menu = menuRepositoryHelper.findByRestaurantIdAndById(restaurantId, menuId);
    menuMapper.updateEntity(menu, request);

    return "Menu with ID " + menuId + " of restaurant ID " + restaurantId + " updated successfully";
  }

  @Transactional
  public String deleteMenu(Long restaurantId, Long menuId) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    Menu menu = menuRepositoryHelper.findByRestaurantIdAndById(restaurantId, menuId);
    menuRepository.delete(menu);

    return "Menu with ID " + menuId + " of restaurant ID " + restaurantId + " deleted successfully";
  }

}
