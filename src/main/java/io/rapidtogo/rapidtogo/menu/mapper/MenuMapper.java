package io.rapidtogo.rapidtogo.menu.mapper;

import io.rapidtogo.rapidtogo.menu.dto.MenuRequest;
import io.rapidtogo.rapidtogo.menu.dto.MenuResponse;
import io.rapidtogo.rapidtogo.menu.model.Menu;
import io.rapidtogo.rapidtogo.product.mapper.ProductMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MenuMapper {

  private final ProductMapper productMapper;

  public Menu mapToEntity(MenuRequest request) {

    if (request == null) {
      return null;
    }

    Menu menu = new Menu();
    menu.setName(request.getName());
    menu.setDescription(request.getDescription());

    return menu;
  }

  public MenuResponse mapToDto(Menu menu) {

    if (menu == null) {
      return null;
    }

    MenuResponse menuResponse = new MenuResponse();
    menuResponse.setId(menu.getId());
    menuResponse.setName(menu.getName());
    menuResponse.setDescription(menu.getDescription());
    menuResponse.setProducts(productMapper.mapToListMinimalDto(menu.getProducts()));

    return menuResponse;
  }

  public List<MenuResponse> mapToListDto(List<Menu> menus) {

    if (menus == null || menus.isEmpty()) {
      return new ArrayList<>();
    }

    return menus.stream()
        .map(this::mapToDto)
        .toList();
  }

  /**
   * Update the entity with the given request data including the name and description
   *
   * @param menu    Menu entity to update
   * @param request Menu request
   */
  public void updateEntity(Menu menu, MenuRequest request) {

    if (request == null) {
      return;
    }

    menu.setName(request.getName());
    menu.setDescription(request.getDescription());
  }
}
