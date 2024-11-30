package io.rapidtogo.rapidtogo.restaurant.mapper;

import io.rapidtogo.rapidtogo.address.mapper.AddressMapper;
import io.rapidtogo.rapidtogo.restaurant.enums.Category;
import io.rapidtogo.rapidtogo.restaurant.model.Restaurant;
import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantMapper {

  private final AddressMapper addressMapper;

  public Restaurant mapToEntity(RestaurantCreateRequest request) {

    if (request == null) {
      return null;
    }

    Restaurant restaurant = new Restaurant();
    restaurant.setName(request.getName());
    restaurant.setOpeningTime(request.getOpeningTime());
    restaurant.setClosingTime(request.getClosingTime());
    restaurant.setPickUp(request.isPickUp());
    restaurant.setDelivery(request.isDelivery());
    restaurant.setCategory(Category.valueOf(request.getCategory()));
    restaurant.setAddress(addressMapper.mapToEntity(request.getAddress()));

    if (request.getDeliveryFee() != null) {
      restaurant.setDeliveryFee(request.getDeliveryFee());
    }
    if (request.getPhoneNumber() != null) {
      restaurant.setPhoneNumber(request.getPhoneNumber());
    }
    if (request.getWebsite() != null) {
      restaurant.setWebsite(request.getWebsite());
    }

    if (request.getDescription() != null) {
      restaurant.setDescription(request.getDescription());
    }

    return restaurant;
  }
}
