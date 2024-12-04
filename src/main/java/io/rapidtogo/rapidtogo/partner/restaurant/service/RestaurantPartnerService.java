package io.rapidtogo.rapidtogo.partner.restaurant.service;

import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantCreateRequest;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantDetailedResponse;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantMinimalResponse;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantUpdateRequest;
import io.rapidtogo.rapidtogo.partner.restaurant.mapper.RestaurantMapper;
import io.rapidtogo.rapidtogo.partner.restaurant.model.Restaurant;
import io.rapidtogo.rapidtogo.partner.restaurant.repository.RestaurantRepository;
import io.rapidtogo.rapidtogo.partner.restaurant.repository.RestaurantRepositoryHelper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantPartnerService {

  private final RestaurantRepository restaurantRepository;
  private final RestaurantMapper restaurantMapper;
  private final RestaurantRepositoryHelper restaurantRepositoryHelper;

  @Transactional
  public String createRestaurant(RestaurantCreateRequest request) {

    restaurantRepository.save(restaurantMapper.mapToEntity(request));

    return "Restaurant created successfully";
  }

  @Transactional(readOnly = true)
  public List<RestaurantMinimalResponse> getAllRestaurants() {

    return restaurantMapper.mapToMinimalListDto(restaurantRepositoryHelper.findAll());
  }

  @Transactional(readOnly = true)
  public RestaurantDetailedResponse getRestaurantById(Long restaurantId) {

    Restaurant restaurant = restaurantRepositoryHelper.findById(restaurantId);

    return restaurantMapper.mapToDetailedDto(restaurant);
  }

  @Transactional(readOnly = true)
  public List<RestaurantMinimalResponse> getAllRestaurantsByCity(String city) {

    return restaurantMapper.mapToMinimalListDto(restaurantRepositoryHelper.findAllByCity(city));
  }

  @Transactional
  public String updateRestaurantById(Long restaurantId, RestaurantUpdateRequest request) {

    Restaurant restaurant = restaurantRepositoryHelper.findById(restaurantId);
    restaurant = restaurantMapper.updateEntity(restaurant, request);
    restaurantRepository.save(restaurant);

    return "Restaurant updated successfully";
  }

  @Transactional
  public String deleteRestaurantById(Long restaurantId) {

    restaurantRepositoryHelper.deleteById(restaurantId);

    return "Restaurant deleted successfully";
  }

}
