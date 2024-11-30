package io.rapidtogo.rapidtogo.restaurant.mapper;

import io.rapidtogo.rapidtogo.address.mapper.AddressMapper;
import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantCreateRequest;
import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantDetailedResponse;
import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantMinimalResponse;
import io.rapidtogo.rapidtogo.restaurant.dto.RestaurantUpdateRequest;
import io.rapidtogo.rapidtogo.restaurant.enums.Category;
import io.rapidtogo.rapidtogo.restaurant.model.Restaurant;
import java.util.List;
import java.util.stream.Collectors;
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

  public List<RestaurantMinimalResponse> mapToMinimalListDto(List<Restaurant> restaurants) {

    if (restaurants == null) {
      return null;
    }

    return restaurants.stream()
        .map(this::mapToMinimalDto)
        .collect(Collectors.toList());
  }

  public RestaurantMinimalResponse mapToMinimalDto(Restaurant restaurant) {

    if (restaurant == null) {
      return null;
    }

    RestaurantMinimalResponse restaurantMinimalResponse = new RestaurantMinimalResponse();
    restaurantMinimalResponse.setId(restaurant.getId());
    restaurantMinimalResponse.setName(restaurant.getName());
    restaurantMinimalResponse.setCategory(restaurant.getCategory());
    restaurantMinimalResponse.setDeliveryFee(restaurant.getDeliveryFee());
    restaurantMinimalResponse.setScore(restaurant.getScore());
    restaurantMinimalResponse.setMinimalOrder(restaurant.getMinimalOrder());
    restaurantMinimalResponse.setReviewCount(restaurant.getReviewCount());

    return restaurantMinimalResponse;
  }

  public RestaurantDetailedResponse mapToDetailedDto(Restaurant restaurant) {

    if (restaurant == null) {
      return null;
    }

    RestaurantDetailedResponse restaurantDetailedResponse = new RestaurantDetailedResponse();
    restaurantDetailedResponse.setId(restaurant.getId());
    restaurantDetailedResponse.setName(restaurant.getName());
    restaurantDetailedResponse.setPickUp(restaurant.isPickUp());
    restaurantDetailedResponse.setDelivery(restaurant.isDelivery());
    restaurantDetailedResponse.setCategory(restaurant.getCategory().name());
    restaurantDetailedResponse.setDeliveryFee(restaurant.getDeliveryFee());
    restaurantDetailedResponse.setPhoneNumber(restaurant.getPhoneNumber());
    restaurantDetailedResponse.setWebsite(restaurant.getWebsite());
    restaurantDetailedResponse.setDescription(restaurant.getDescription());
    restaurantDetailedResponse.setScore(restaurant.getScore());
    restaurantDetailedResponse.setMinimalOrder(restaurant.getMinimalOrder());
    restaurantDetailedResponse.setReviewCount(restaurant.getReviewCount());
    restaurantDetailedResponse.setAddress(addressMapper.mapToDto(restaurant.getAddress()));

    return restaurantDetailedResponse;
  }

  public Restaurant updateEntity(Restaurant restaurant, RestaurantUpdateRequest request) {

    if (restaurant == null || request == null) {
      return null;
    }

    restaurant.setName(request.getName());
    restaurant.setPickUp(request.isPickUp());
    restaurant.setDelivery(request.isDelivery());
    restaurant.setCategory(Category.valueOf(request.getCategory()));
    restaurant.setMinimalOrder(request.getMinimalOrder());
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
