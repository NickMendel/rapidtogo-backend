package io.rapidtogo.rapidtogo.restaurant.repository;

import io.rapidtogo.rapidtogo.restaurant.model.Restaurant;
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
  public List<Restaurant> getAll() {

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
  public Restaurant getById(Long restaurantId) {
    return restaurantRepository.findById(restaurantId).orElseThrow(
        () -> new EntityNotFoundException("Restaurant with ID: " + restaurantId + " not found"));
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
}
