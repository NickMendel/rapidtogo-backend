package io.rapidtogo.rapidtogo.restaurant.controller;

import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantCreateRequest;
import io.rapidtogo.rapidtogo.restaurant.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/restaurants")
@CrossOrigin(origins = "*")
@Slf4j
@RequiredArgsConstructor
public class RestaurantController {

  private final RestaurantService restaurantService;

  @PostMapping
  public ResponseEntity<String> createRestaurant(@RequestBody @Valid RestaurantCreateRequest request) {

    String successMessage = restaurantService.createRestaurant(request);
    log.info("Restaurant with name {} created successfully.", request.getName());

    return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
  }


}
