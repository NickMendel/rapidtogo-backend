package io.rapidtogo.rapidtogo.customer.restaurant.service;

import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantDetailedResponse;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantMinimalResponse;
import io.rapidtogo.rapidtogo.partner.restaurant.mapper.RestaurantMapper;
import io.rapidtogo.rapidtogo.partner.restaurant.model.Restaurant;
import io.rapidtogo.rapidtogo.partner.restaurant.repository.RestaurantRepositoryHelper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantCustomerService {

  private final RestaurantRepositoryHelper restaurantRepositoryHelper;
  private final RestaurantMapper restaurantMapper;

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

}
