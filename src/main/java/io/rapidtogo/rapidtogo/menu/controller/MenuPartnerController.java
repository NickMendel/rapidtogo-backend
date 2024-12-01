package io.rapidtogo.rapidtogo.menu.controller;

import io.rapidtogo.rapidtogo.menu.dto.MenuRequest;
import io.rapidtogo.rapidtogo.menu.dto.MenuResponse;
import io.rapidtogo.rapidtogo.menu.service.MenuService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/partners")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class MenuPartnerController {

  private final MenuService menuService;

  @PostMapping("/restaurants/{restaurantId}/menus")
  public ResponseEntity<String> createMenu(@PathVariable Long restaurantId,
      @RequestBody @Valid MenuRequest request) {

    String successMessage = menuService.createMenu(restaurantId, request);
    log.info("Menu for restaurant ID {} created successfully", restaurantId);

    return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
  }

  @GetMapping("/restaurants/{restaurantId}/menus")
  public ResponseEntity<List<MenuResponse>> getAllMenusByRestaurantId(
      @PathVariable Long restaurantId) {

    List<MenuResponse> menus = menuService.getAllMenusByRestaurantId(restaurantId);
    log.info("Menus for restaurant ID {} fetched successfully", restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(menus);
  }

  @GetMapping("/restaurants/{restaurantId}/menus/{menuId}")
  public ResponseEntity<MenuResponse> getMenuByRestaurantIdAndMenuId(
      @PathVariable Long restaurantId, @PathVariable Long menuId) {

    MenuResponse menu = menuService.getMenuByRestaurantIdAndMenuId(restaurantId, menuId);
    log.info("Menu with ID {} for restaurant ID {} fetched successfully", menuId, restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(menu);
  }

  @PatchMapping("/restaurants/{restaurantId}/menus/{menuId}")
  public ResponseEntity<String> updateMenu(@PathVariable Long restaurantId,
      @PathVariable Long menuId, @RequestBody @Valid MenuRequest request) {

    String successMessage = menuService.updateMenu(restaurantId, menuId, request);
    log.info("Menu with ID {} for restaurant ID {} updated successfully", menuId, restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(successMessage);
  }

  @DeleteMapping("/restaurants/{restaurantId}/menus/{menuId}")
  public ResponseEntity<String> deleteMenu(@PathVariable Long restaurantId,
      @PathVariable Long menuId) {

    String successMessage = menuService.deleteMenu(restaurantId, menuId);
    log.info("Menu with ID {} for restaurant ID {} deleted successfully", menuId, restaurantId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
  }
}
