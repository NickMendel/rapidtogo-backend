package io.rapidtogo.rapidtogo.partner.menu.dto;

import io.rapidtogo.rapidtogo.partner.menu_item.dto.MenuItemMinimalResponse;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuResponse {

  private Long id;
  private String name;
  private String description;
  private boolean active;
  private List<MenuItemMinimalResponse> menuItems;
}
