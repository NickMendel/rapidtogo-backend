package io.rapidtogo.rapidtogo.partner.restaurant.repository;

import io.rapidtogo.rapidtogo.partner.restaurant.model.Restaurant;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantRepositoryHelper {

  private final RestaurantRepository restaurantRepository;

  /**
   * Get all restaurants
   *
   * @return List of all restaurants
   * @throws EntityNotFoundException if no restaurants are found
   */
  public List<Restaurant> findAll() {

    List<Restaurant> restaurants = restaurantRepository.findAll();

    if (restaurants.isEmpty()) {
      throw new EntityNotFoundException("No restaurants found");
    }

    return restaurants;
  }

  /**
   * Get restaurant by ID
   *
   * @param restaurantId ID of the restaurant
   * @return Restaurant with the given ID
   * @throws EntityNotFoundException if no restaurant is found with the given ID
   */
  public Restaurant findById(Long restaurantId) {
    return restaurantRepository.findById(restaurantId).orElseThrow(
        () -> new EntityNotFoundException("Restaurant with ID: " + restaurantId + " not found"));
  }

  public List<Restaurant> findAllByCity(String city) {

    List<Restaurant> restaurants = restaurantRepository.findAllByCity(city);

    if (restaurants.isEmpty()) {
      throw new EntityNotFoundException("No restaurants found in city: " + city);
    }

    return restaurants;
  }

  /**
   * Delete restaurant by ID
   *
   * @param restaurantId ID of the restaurant
   * @throws EntityNotFoundException if no restaurant is found with the given ID
   */
  public void deleteById(Long restaurantId) {

    if (!restaurantRepository.existsById(restaurantId)) {
      throw new EntityNotFoundException("Restaurant with ID: " + restaurantId + " not found");
    }

    restaurantRepository.deleteById(restaurantId);
  }

  /**
   * Check if restaurant exists by ID
   *
   * @param restaurantId ID of the restaurant
   * @throws EntityNotFoundException if no restaurant is found with the given ID
   */
  public void checkExistence(Long restaurantId) {

    if (!restaurantRepository.existsById(restaurantId)) {
      throw new EntityNotFoundException("Restaurant with ID: " + restaurantId + " not found");
    }
  }
}
