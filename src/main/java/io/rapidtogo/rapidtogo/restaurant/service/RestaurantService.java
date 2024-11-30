package io.rapidtogo.rapidtogo.restaurant.service;

import io.rapidtogo.rapidtogo.restaurant.mapper.RestaurantMapper;
import io.rapidtogo.rapidtogo.restaurant.repository.RestaurantRepository;
import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RestaurantService {

  private final RestaurantRepository restaurantRepository;
  private final RestaurantMapper restaurantMapper;

  @Transactional
  public String createRestaurant(RestaurantCreateRequest request) {

    restaurantRepository.save(restaurantMapper.mapToEntity(request));

    return "Restaurant created successfully";
  }

}
