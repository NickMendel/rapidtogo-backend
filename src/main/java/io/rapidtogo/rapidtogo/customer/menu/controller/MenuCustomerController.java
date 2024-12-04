package io.rapidtogo.rapidtogo.customer.menu.controller;

import io.rapidtogo.rapidtogo.partner.menu.dto.MenuResponse;
import io.rapidtogo.rapidtogo.partner.menu.service.MenuService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  private final MenuService menuService;

  @GetMapping("/restaurants/{restaurantId}/menus")
  public ResponseEntity<List<MenuResponse>> getAllMenusByRestaurantId(
      @PathVariable Long restaurantId) {

    List<MenuResponse> menus = menuService.getAllMenusByRestaurantId(restaurantId);
    log.info("Menus for restaurant ID {} fetched successfully", restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(menus);
  }
}
