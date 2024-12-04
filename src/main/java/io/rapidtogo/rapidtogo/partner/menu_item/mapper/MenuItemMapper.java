package io.rapidtogo.rapidtogo.partner.menu_item.mapper;

import io.rapidtogo.rapidtogo.partner.menu_item.dto.MenuItemMinimalResponse;
import io.rapidtogo.rapidtogo.partner.menu_item.dto.MenuItemRequest;
import io.rapidtogo.rapidtogo.partner.menu_item.enums.Allergy;
import io.rapidtogo.rapidtogo.partner.menu_item.model.MenuItem;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class MenuItemMapper {

  public MenuItem mapToEntity(MenuItemRequest request) {

    if (request == null) {
      return null;
    }

    MenuItem menuItem = new MenuItem();
    menuItem.setName(request.getName());
    menuItem.setDescription(request.getDescription());
    menuItem.setPrice(request.getPrice());

    if (request.getAllergies() != null && !request.getAllergies().isEmpty()) {
      menuItem.setAllergies(
          request.getAllergies().stream().map(Allergy::valueOf).collect(Collectors.toSet()));
    }

    return menuItem;
  }

  public MenuItemMinimalResponse mapToMinimalDto(MenuItem menuItem) {

    if (menuItem == null) {
      return null;
    }

    MenuItemMinimalResponse menuItemMinimalResponse = new MenuItemMinimalResponse();
    menuItemMinimalResponse.setId(menuItem.getId());
    menuItemMinimalResponse.setName(menuItem.getName());
    menuItemMinimalResponse.setPrice(menuItem.getPrice());

    return menuItemMinimalResponse;
  }

  public List<MenuItemMinimalResponse> mapToListMinimalDto(List<MenuItem> menuItems) {

    if (menuItems == null || menuItems.isEmpty()) {
      return new ArrayList<>();
    }

    return menuItems.stream().map(this::mapToMinimalDto).toList();
  }

  public void updateEntity(MenuItem menuItem, MenuItemRequest request) {

    if (menuItem == null || request == null) {
      return;
    }

    menuItem.setName(request.getName());
    menuItem.setDescription(request.getDescription());
    menuItem.setPrice(request.getPrice());

    if (request.getAllergies() != null && !request.getAllergies().isEmpty()) {
      menuItem.setAllergies(
          request.getAllergies().stream().map(Allergy::valueOf).collect(Collectors.toSet()));
    }
  }
}
