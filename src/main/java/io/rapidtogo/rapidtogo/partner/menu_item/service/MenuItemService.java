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
  public String createProduct(Long menuId, MenuItemRequest request) {

    Menu menu = menuRepositoryHelper.findById(menuId);
    menuItemRepositoryHelper.checkExistenceByNameAndMenuId(request.getName(), menuId);
    MenuItem menuItem = menuItemMapper.mapToEntity(request);
    menuItem.setMenu(menu);
    menu.getMenuItems().add(menuItem);
    menuRepository.save(menu);

    return "MenuItem with name " + request.getName() + " created successfully.";
  }

  @Transactional
  public String updateProduct(Long menuId, Long productId, MenuItemRequest request) {

    menuRepositoryHelper.checkExistence(menuId);
    MenuItem menuItem = menuItemRepositoryHelper.findByIdAndMenuId(productId, menuId);
    menuItemMapper.updateEntity(menuItem, request);

    return "MenuItem with ID " + productId + " updated successfully.";
  }

  @Transactional
  public String deleteProduct(Long menuId, Long productId) {

    menuRepositoryHelper.checkExistence(menuId);
    MenuItem menuItem = menuItemRepositoryHelper.findByIdAndMenuId(productId, menuId);
    menuItemRepository.delete(menuItem);

    return "MenuItem with ID " + productId + " deleted successfully.";
  }
}
