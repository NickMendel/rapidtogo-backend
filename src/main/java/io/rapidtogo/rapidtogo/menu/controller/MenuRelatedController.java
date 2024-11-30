package io.rapidtogo.rapidtogo.menu.controller;

import io.rapidtogo.rapidtogo.menu.dto.MenuCreateRequest;
import io.rapidtogo.rapidtogo.menu.dto.MenuResponse;
import io.rapidtogo.rapidtogo.menu.service.MenuService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Slf4j
public class MenuRelatedController {

  private final MenuService menuService;

  @PostMapping("/restaurants/{restaurantId}/menus")
  public ResponseEntity<String> createMenu(@PathVariable Long restaurantId,
      @RequestBody @Valid MenuCreateRequest request) {

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
}
