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
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuPartnerService {

  private final MenuRepositoryHelper menuRepositoryHelper;
  private final RestaurantRepositoryHelper restaurantRepositoryHelper;
  private final MenuRepository menuRepository;
  private final MenuMapper menuMapper;

  @Transactional
  public String createMenu(Long restaurantId, MenuRequest request, Jwt jwt) {

    String userId = jwt.getSubject();
    Restaurant restaurant = restaurantRepositoryHelper.findById(restaurantId, userId);
    Menu menu = menuMapper.mapToEntity(request, userId);
    restaurant.getMenus().add(menu);
    menu.setRestaurant(restaurant);

    return "Menu for restaurant ID " + restaurantId + " created successfully";
  }

  @Transactional(readOnly = true)
  public List<MenuResponse> getAllMenusByRestaurantId(Long restaurantId, Jwt jwt) {

    String userId = jwt.getSubject();
    restaurantRepositoryHelper.checkExistence(restaurantId, userId);
    List<Menu> menus = menuRepositoryHelper.findAllByRestaurantId(restaurantId, userId);

    return menuMapper.mapToListDto(menus);
  }

  @Transactional(readOnly = true)
  public MenuResponse getMenuByIdAndByRestaurantId(Long menuId, Long restaurantId, Jwt jwt) {

    String userId = jwt.getSubject();
    restaurantRepositoryHelper.checkExistence(restaurantId, userId);
    Menu menu = menuRepositoryHelper.findByRestaurantIdAndById(restaurantId, menuId, userId);

    return menuMapper.mapToDto(menu);
  }

  @Transactional
  public String updateMenu(Long restaurantId, Long menuId, MenuRequest request, Jwt jwt) {

    String userId = jwt.getSubject();
    restaurantRepositoryHelper.checkExistence(restaurantId, userId);
    Menu menu = menuRepositoryHelper.findByRestaurantIdAndById(restaurantId, menuId, userId);
    menuMapper.updateEntity(menu, request);

    return "Menu with ID " + menuId + " of restaurant ID " + restaurantId + " updated successfully";
  }

  @Transactional
  public String deleteMenu(Long restaurantId, Long menuId, Jwt jwt) {

    String userId = jwt.getSubject();
    restaurantRepositoryHelper.checkExistence(restaurantId, userId);
    Menu menu = menuRepositoryHelper.findByRestaurantIdAndById(restaurantId, menuId, userId);
    menuRepository.delete(menu);

    return "Menu with ID " + menuId + " of restaurant ID " + restaurantId + " deleted successfully";
  }
}
