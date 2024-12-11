package io.rapidtogo.rapidtogo.partner.menu_item.controller;

import io.rapidtogo.rapidtogo.partner.menu_item.dto.MenuItemRequest;
import io.rapidtogo.rapidtogo.partner.menu_item.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/partners")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class MenuItemPartnerController {

  private final MenuItemService menuItemService;

  @PostMapping("/menus/{menuId}/menu-items")
  public ResponseEntity<String> createMenuItem(@PathVariable Long menuId,
      @RequestBody @Valid MenuItemRequest request, @AuthenticationPrincipal Jwt jwt) {

    String successMessage = menuItemService.createMenuItem(menuId, request, jwt);
    log.info("Menu Item with name {} created successfully.", request.getName());

    return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
  }

  @PutMapping("/menus/{menuId}/menu-items/{menuItemId}")
  public ResponseEntity<String> updateMenuItem(@PathVariable Long menuId,
      @PathVariable Long menuItemId, @RequestBody @Valid MenuItemRequest request,
      @AuthenticationPrincipal Jwt jwt) {

    String successMessage = menuItemService.updateMenuItem(menuId, menuItemId, request, jwt);
    log.info("Menu Item with ID {} updated successfully.", menuItemId);

    return ResponseEntity.status(HttpStatus.OK).body(successMessage);
  }

  @DeleteMapping("/menus/{menuId}/menu-items/{menuItemId}")
  public ResponseEntity<String> deleteMenuItem(@PathVariable Long menuId,
      @PathVariable Long menuItemId, @AuthenticationPrincipal Jwt jwt) {

    String successMessage = menuItemService.deleteMenuItem(menuId, menuItemId, jwt);
    log.info("Menu Item with ID {} deleted successfully.", menuItemId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
  }
}
