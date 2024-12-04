package io.rapidtogo.rapidtogo.partner.restaurant.controller;

import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantCreateRequest;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantDetailedResponse;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantMinimalResponse;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantUpdateRequest;
import io.rapidtogo.rapidtogo.partner.restaurant.service.RestaurantPartnerService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/partner/restaurants")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class RestaurantPartnerController {

  private final RestaurantPartnerService restaurantPartnerService;

  @PostMapping
  public ResponseEntity<String> createRestaurant(
      @RequestBody @Valid RestaurantCreateRequest request) {

    String successMessage = restaurantPartnerService.createRestaurant(request);
    log.info("Restaurant with name {} created successfully.", request.getName());

    return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
  }

  @GetMapping
  public ResponseEntity<List<RestaurantMinimalResponse>> getAllRestaurants() {

    List<RestaurantMinimalResponse> restaurants = restaurantPartnerService.getAllRestaurants();
    log.info("Retrieved {} restaurants.", restaurants.size());

    return ResponseEntity.status(HttpStatus.OK).body(restaurants);
  }

  @GetMapping("/{restaurantId}")
  public ResponseEntity<RestaurantDetailedResponse> getRestaurantById(
      @PathVariable Long restaurantId) {

    RestaurantDetailedResponse restaurant = restaurantPartnerService.getRestaurantById(
        restaurantId);
    log.info("Retrieved restaurant with id {}.", restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(restaurant);
  }

  @GetMapping(params = "location")
  public ResponseEntity<List<RestaurantMinimalResponse>> getAllRestaurantsByLocation(
      @RequestParam String location) {

    List<RestaurantMinimalResponse> restaurants = restaurantPartnerService.getAllRestaurantsByCity(
        location);
    log.info("Retrieved {} restaurants in city {}.", restaurants.size(), location);

    return ResponseEntity.status(HttpStatus.OK).body(restaurants);
  }

  @PutMapping("/{restaurantId}")
  public ResponseEntity<String> updateRestaurant(@PathVariable Long restaurantId,
      @RequestBody @Valid RestaurantUpdateRequest request) {

    String successMessage = restaurantPartnerService.updateRestaurantById(restaurantId, request);
    log.info("Restaurant with id {} updated successfully.", restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(successMessage);
  }

  @DeleteMapping("/{restaurantId}")
  public ResponseEntity<String> deleteRestaurant(@PathVariable Long restaurantId) {

    String successMessage = restaurantPartnerService.deleteRestaurantById(restaurantId);
    log.info("Restaurant with id {} deleted successfully.", restaurantId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
  }
}
