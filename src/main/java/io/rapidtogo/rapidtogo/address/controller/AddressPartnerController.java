package io.rapidtogo.rapidtogo.address.controller;

import io.rapidtogo.rapidtogo.address.dto.AddressRequest;
import io.rapidtogo.rapidtogo.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/partners")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class AddressPartnerController {

  private final AddressService addressService;

  @PutMapping("/restaurants/{restaurantId}/address")
  public ResponseEntity<String> updateRestaurantAddress(@PathVariable Long restaurantId,
      @RequestBody AddressRequest request) {

    String successMessage = addressService.updateRestaurantAddress(restaurantId, request);
    log.info("Address for restaurant ID {} updated successfully", restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(successMessage);
  }
}
