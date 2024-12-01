package io.rapidtogo.rapidtogo.restaurant.controller;

import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantCreateRequest;
import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantDetailedResponse;
import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantMinimalResponse;
import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantUpdateRequest;
import io.rapidtogo.rapidtogo.restaurant.service.RestaurantService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/partners")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class RestaurantPartnerController {

  private final RestaurantService restaurantService;

  @PostMapping("/restaurants")
  public ResponseEntity<String> createRestaurant(
      @RequestBody @Valid RestaurantCreateRequest request) {

    String successMessage = restaurantService.createRestaurant(request);
    log.info("Restaurant with name {} created successfully.", request.getName());

    return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
  }

  @GetMapping("/restaurants")
  public ResponseEntity<List<RestaurantMinimalResponse>> getAllRestaurants() {

    List<RestaurantMinimalResponse> restaurants = restaurantService.getAllRestaurants();
    log.info("Retrieved {} restaurants.", restaurants.size());

    return ResponseEntity.status(HttpStatus.OK).body(restaurants);
  }

  @GetMapping("/restaurants/{restaurantId}")
  public ResponseEntity<RestaurantDetailedResponse> getRestaurantById(
      @PathVariable Long restaurantId) {

    RestaurantDetailedResponse restaurant = restaurantService.getRestaurantById(restaurantId);
    log.info("Retrieved restaurant with id {}.", restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(restaurant);
  }

  @PutMapping("/restaurants/{restaurantId}")
  public ResponseEntity<String> updateRestaurant(@PathVariable Long restaurantId,
      @RequestBody @Valid RestaurantUpdateRequest request) {

    String successMessage = restaurantService.updateRestaurantById(restaurantId, request);
    log.info("Restaurant with id {} updated successfully.", restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(successMessage);
  }

  @DeleteMapping("/restaurants/{id}")
  public ResponseEntity<String> deleteRestaurant(@PathVariable Long restaurantId) {

    String successMessage = restaurantService.deleteRestaurantById(restaurantId);
    log.info("Restaurant with id {} deleted successfully.", restaurantId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
  }

}
