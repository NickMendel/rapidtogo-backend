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
   * Get all restaurants owned by a user
   *
   * @param userId ID of the user who owns the restaurants
   * @return List of all restaurants owned by the user
   * @throws EntityNotFoundException if no restaurants are found for the user
   */
  public List<Restaurant> findAll(String userId) {

    List<Restaurant> restaurants = restaurantRepository.findAll(userId);

    if (restaurants.isEmpty()) {
      throw new EntityNotFoundException("No restaurants found");
    }

    return restaurants;
  }

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
   * Get restaurant by ID owned by a user
   *
   * @param restaurantId ID of the restaurant
   * @param userId       ID of the user who owns the restaurant
   * @return Restaurant with the given ID
   * @throws EntityNotFoundException if no restaurant is found with the given ID
   */
  public Restaurant findById(Long restaurantId, String userId) {

    return restaurantRepository.findById(restaurantId, userId).orElseThrow(
        () -> new EntityNotFoundException("Restaurant with ID: " + restaurantId + " not found"));
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

  /**
   * Get all restaurants in a city owned by a user
   *
   * @param city   City name
   * @param userId ID of the user who owns the restaurants
   * @return List of all restaurants in the city
   * @throws EntityNotFoundException if no restaurants are found in the city
   */
  public List<Restaurant> findAllByCity(String city, String userId) {

    List<Restaurant> restaurants = restaurantRepository.findAllByCity(city, userId);

    if (restaurants.isEmpty()) {
      throw new EntityNotFoundException("No restaurants found in city: " + city);
    }

    return restaurants;
  }

  /**
   * Get all restaurants in a city
   *
   * @param city City name
   * @return List of all restaurants in the city
   * @throws EntityNotFoundException if no restaurants are found in the city
   */
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
  public void deleteById(Long restaurantId, String userId) {

    if (!restaurantRepository.existsById(restaurantId, userId)) {
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

  /**
   * Check if restaurant exists by ID owned by a user
   *
   * @param restaurantId ID of the restaurant
   * @param userId       ID of the user who owns the restaurant
   * @throws EntityNotFoundException if no restaurant is found with the given ID owned by the user
   */
  public void checkExistence(Long restaurantId, String userId) {

    if (!restaurantRepository.existsById(restaurantId, userId)) {
      throw new EntityNotFoundException("Restaurant with ID: " + restaurantId + " not found");
    }
  }
}
