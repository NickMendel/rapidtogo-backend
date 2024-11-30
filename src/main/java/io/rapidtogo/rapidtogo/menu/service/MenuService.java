package io.rapidtogo.rapidtogo.menu.service;

import io.rapidtogo.rapidtogo.menu.dto.MenuCreateRequest;
import io.rapidtogo.rapidtogo.menu.dto.MenuResponse;
import io.rapidtogo.rapidtogo.menu.mapper.MenuMapper;
import io.rapidtogo.rapidtogo.menu.model.Menu;
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
  private final MenuMapper menuMapper;

  @Transactional
  public String createMenu(Long restaurantId, MenuCreateRequest request) {

    Restaurant restaurant = restaurantRepositoryHelper.getById(restaurantId);
    Menu menu = menuMapper.mapToEntity(request);
    restaurant.getMenus().add(menu);
    menu.setRestaurant(restaurant);

    return "Menu for restaurant " + restaurant.getName() + " created successfully";
  }

  @Transactional(readOnly = true)
  public List<MenuResponse> getAllMenusByRestaurantId(Long restaurantId) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    List<Menu> menus = menuRepositoryHelper.getAllByRestaurantId(restaurantId);

    return menuMapper.mapToListDto(menus);
  }

}
