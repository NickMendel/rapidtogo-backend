package io.rapidtogo.rapidtogo.customer.menu.service;

import io.rapidtogo.rapidtogo.partner.menu.dto.MenuResponse;
import io.rapidtogo.rapidtogo.partner.menu.mapper.MenuMapper;
import io.rapidtogo.rapidtogo.partner.menu.model.Menu;
import io.rapidtogo.rapidtogo.partner.menu.repository.MenuRepositoryHelper;
import io.rapidtogo.rapidtogo.partner.restaurant.repository.RestaurantRepositoryHelper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuCustomerService {

  private final MenuRepositoryHelper menuRepositoryHelper;
  private final RestaurantRepositoryHelper restaurantRepositoryHelper;
  private final MenuMapper menuMapper;

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
}
