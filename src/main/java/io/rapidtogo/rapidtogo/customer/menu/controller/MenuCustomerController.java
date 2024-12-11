package io.rapidtogo.rapidtogo.customer.menu.controller;

import io.rapidtogo.rapidtogo.customer.menu.service.MenuCustomerService;
import io.rapidtogo.rapidtogo.partner.menu.dto.MenuResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class MenuCustomerController {

  private final MenuCustomerService menuCustomerService;

  @GetMapping("/restaurants/{restaurantId}/menus")
  public ResponseEntity<List<MenuResponse>> getAllMenusByRestaurantId(
      @PathVariable Long restaurantId, @AuthenticationPrincipal Jwt jwt) {

    List<MenuResponse> menus = menuCustomerService.getAllMenusByRestaurantId(restaurantId);
    log.info("Menus for restaurant ID {} fetched successfully", restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(menus);
  }

  @GetMapping("/restaurants/{restaurantId}/menus/{menuId}")
  public ResponseEntity<MenuResponse> getMenuByIdAndByRestaurantId(
      @PathVariable Long restaurantId, @PathVariable Long menuId,
      @AuthenticationPrincipal Jwt jwt) {

    MenuResponse menu = menuCustomerService.getMenuByIdAndByRestaurantId(menuId, restaurantId);
    log.info("Menu with ID {} for restaurant ID {} fetched successfully", menuId, restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(menu);
  }
}
