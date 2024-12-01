package io.rapidtogo.rapidtogo.address.service;

import io.rapidtogo.rapidtogo.address.dto.AddressRequest;
import io.rapidtogo.rapidtogo.address.mapper.AddressMapper;
import io.rapidtogo.rapidtogo.restaurant.model.Restaurant;
import io.rapidtogo.rapidtogo.restaurant.repository.RestaurantRepository;
import io.rapidtogo.rapidtogo.restaurant.repository.RestaurantRepositoryHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AddressService {

  private final RestaurantRepositoryHelper restaurantRepositoryHelper;
  private final AddressMapper addressMapper;
  private final RestaurantRepository restaurantRepository;

  @Transactional
  public String updateRestaurantAddress(Long restaurantId, AddressRequest request) {

    Restaurant restaurant = restaurantRepositoryHelper.findById(restaurantId);
    restaurant.setAddress(addressMapper.updateEntity(request, restaurant.getAddress()));
    restaurantRepository.save(restaurant);

    return "Address of restaurant ID " + restaurantId + " updated successfully";
  }
}
