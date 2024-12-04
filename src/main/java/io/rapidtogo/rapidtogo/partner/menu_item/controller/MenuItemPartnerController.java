package io.rapidtogo.rapidtogo.partner.menu_item.controller;

import io.rapidtogo.rapidtogo.partner.menu_item.dto.MenuItemRequest;
import io.rapidtogo.rapidtogo.partner.menu_item.service.MenuItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

  @PostMapping("/menus/{menuId}/products")
  public ResponseEntity<String> createProduct(@PathVariable Long menuId,
      @RequestBody @Valid MenuItemRequest request) {

    String successMessage = menuItemService.createProduct(menuId, request);
    log.info("Menu Item with name {} created successfully.", request.getName());

    return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
  }

  @PutMapping("/menus/{menuId}/products/{productId}")
  public ResponseEntity<String> updateProduct(@PathVariable Long menuId,
      @PathVariable Long productId, @RequestBody @Valid MenuItemRequest request) {

    String successMessage = menuItemService.updateProduct(menuId, productId, request);
    log.info("Menu Item with ID {} updated successfully.", productId);

    return ResponseEntity.status(HttpStatus.OK).body(successMessage);
  }

  @DeleteMapping("/menus/{menuId}/products/{productId}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long menuId,
      @PathVariable Long productId) {

    String successMessage = menuItemService.deleteProduct(menuId, productId);
    log.info("Menu Item with ID {} deleted successfully.", productId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
  }
}
