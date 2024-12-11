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
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantPartnerService {

  private final RestaurantRepository restaurantRepository;
  private final RestaurantMapper restaurantMapper;
  private final RestaurantRepositoryHelper restaurantRepositoryHelper;

  @Transactional
  public String createRestaurant(RestaurantCreateRequest request, Jwt jwt) {

    restaurantRepository.save(restaurantMapper.mapToEntity(request, jwt.getSubject()));

    return "Restaurant created successfully";
  }

  @Transactional(readOnly = true)
  public List<RestaurantMinimalResponse> getAllRestaurants(Jwt jwt) {

    return restaurantMapper.mapToMinimalListDto(
        restaurantRepositoryHelper.findAll(jwt.getSubject()));
  }

  @Transactional(readOnly = true)
  public RestaurantDetailedResponse getRestaurantById(Long restaurantId, Jwt jwt) {

    Restaurant restaurant = restaurantRepositoryHelper.findById(restaurantId, jwt.getSubject());

    return restaurantMapper.mapToDetailedDto(restaurant);
  }

  @Transactional(readOnly = true)
  public List<RestaurantMinimalResponse> getAllRestaurantsByCity(String city, Jwt jwt) {

    return restaurantMapper.mapToMinimalListDto(
        restaurantRepositoryHelper.findAllByCity(city, jwt.getSubject()));
  }

  @Transactional
  public String updateRestaurantById(Long restaurantId, RestaurantUpdateRequest request, Jwt jwt) {

    Restaurant restaurant = restaurantRepositoryHelper.findById(restaurantId, jwt.getSubject());
    restaurant = restaurantMapper.updateEntity(restaurant, request);
    restaurantRepository.save(restaurant);

    return "Restaurant updated successfully";
  }

  @Transactional
  public String deleteRestaurantById(Long restaurantId, Jwt jwt) {

    restaurantRepositoryHelper.deleteById(restaurantId, jwt.getSubject());

    return "Restaurant deleted successfully";
  }

}
