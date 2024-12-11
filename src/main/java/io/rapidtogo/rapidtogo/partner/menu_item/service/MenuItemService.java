package io.rapidtogo.rapidtogo.partner.menu_item.service;

import io.rapidtogo.rapidtogo.partner.menu.model.Menu;
import io.rapidtogo.rapidtogo.partner.menu.repository.MenuRepository;
import io.rapidtogo.rapidtogo.partner.menu.repository.MenuRepositoryHelper;
import io.rapidtogo.rapidtogo.partner.menu_item.dto.MenuItemRequest;
import io.rapidtogo.rapidtogo.partner.menu_item.mapper.MenuItemMapper;
import io.rapidtogo.rapidtogo.partner.menu_item.model.MenuItem;
import io.rapidtogo.rapidtogo.partner.menu_item.repository.MenuItemRepository;
import io.rapidtogo.rapidtogo.partner.menu_item.repository.MenuItemRepositoryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MenuItemService {

  private final MenuItemRepository menuItemRepository;
  private final MenuRepository menuRepository;
  private final MenuItemRepositoryHelper menuItemRepositoryHelper;
  private final MenuItemMapper menuItemMapper;
  private final MenuRepositoryHelper menuRepositoryHelper;

  @Transactional
  public String createMenuItem(Long menuId, MenuItemRequest request, Jwt jwt) {

    String userId = jwt.getSubject();
    Menu menu = menuRepositoryHelper.findById(menuId, userId);
    menuItemRepositoryHelper.checkExistenceByNameAndMenuId(request.getName(), menuId, userId);
    MenuItem menuItem = menuItemMapper.mapToEntity(request, userId);
    menuItem.setMenu(menu);
    menu.getMenuItems().add(menuItem);
    menuRepository.save(menu);

    return "Menu Item with name " + request.getName() + " created successfully.";
  }

  @Transactional
  public String updateMenuItem(Long menuId, Long menuItemId, MenuItemRequest request, Jwt jwt) {

    String userId = jwt.getSubject();
    menuRepositoryHelper.checkExistence(menuId, userId);
    MenuItem menuItem = menuItemRepositoryHelper.findByIdAndMenuId(menuItemId, menuId, userId);
    menuItemMapper.updateEntity(menuItem, request);

    return "Menu Item with ID " + menuItemId + " updated successfully.";
  }

  @Transactional
  public String deleteMenuItem(Long menuId, Long menuItemId, Jwt jwt) {

    String userId = jwt.getSubject();
    menuRepositoryHelper.checkExistence(menuId, userId);
    MenuItem menuItem = menuItemRepositoryHelper.findByIdAndMenuId(menuItemId, menuId, userId);
    menuItemRepository.delete(menuItem);

    return "Menu Item with ID " + menuItemId + " deleted successfully.";
  }
}
