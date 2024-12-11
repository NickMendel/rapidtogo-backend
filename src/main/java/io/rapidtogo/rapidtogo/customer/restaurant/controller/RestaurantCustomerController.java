package io.rapidtogo.rapidtogo.customer.restaurant.controller;

import io.rapidtogo.rapidtogo.customer.restaurant.service.RestaurantCustomerService;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantDetailedResponse;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantMinimalResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customers/restaurants")
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class RestaurantCustomerController {

  private final RestaurantCustomerService restaurantCustomerService;

  @GetMapping
  public ResponseEntity<List<RestaurantMinimalResponse>> getAllRestaurants() {

    List<RestaurantMinimalResponse> restaurants = restaurantCustomerService.getAllRestaurants();
    log.info("Retrieved {} restaurants.", restaurants.size());

    return ResponseEntity.status(HttpStatus.OK).body(restaurants);
  }

  @GetMapping("/{restaurantId}")
  public ResponseEntity<RestaurantDetailedResponse> getRestaurantById(
      @PathVariable Long restaurantId) {

    RestaurantDetailedResponse restaurant = restaurantCustomerService.getRestaurantById(
        restaurantId);
    log.info("Retrieved restaurant with id {}.", restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(restaurant);
  }

  @GetMapping(params = "location")
  public ResponseEntity<List<RestaurantMinimalResponse>> getAllRestaurantsByLocation(
      @RequestParam String location) {

    List<RestaurantMinimalResponse> restaurants = restaurantCustomerService.getAllRestaurantsByCity(
        location);
    log.info("Retrieved {} restaurants in city {}.", restaurants.size(), location);

    return ResponseEntity.status(HttpStatus.OK).body(restaurants);
  }

  // TODO: Add more endpoints with filters and pagination
}
