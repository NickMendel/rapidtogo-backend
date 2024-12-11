package io.rapidtogo.rapidtogo.partner.restaurant.mapper;

import io.rapidtogo.rapidtogo.address.mapper.AddressMapper;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantCreateRequest;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantDetailedResponse;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantMinimalResponse;
import io.rapidtogo.rapidtogo.partner.restaurant.dto.RestaurantUpdateRequest;
import io.rapidtogo.rapidtogo.partner.restaurant.enums.Category;
import io.rapidtogo.rapidtogo.partner.restaurant.model.Restaurant;
import io.rapidtogo.rapidtogo.utils.Date_Time.IntervalFormatter;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RestaurantMapper {

  private final AddressMapper addressMapper;
  private final IntervalFormatter intervalFormatter;

  public Restaurant mapToEntity(RestaurantCreateRequest request, String userId) {

    if (request == null || userId == null) {
      return null;
    }

    Restaurant restaurant = new Restaurant();
    restaurant.setName(request.getName());
    restaurant.setOpeningHours(parseOpeningHours(request.getOpeningHours()));
    restaurant.setPickUp(request.isPickUp());
    restaurant.setDelivery(request.isDelivery());
    restaurant.setCategories(
        request.getCategory().stream().map(Category::valueOf).collect(Collectors.toSet()));
    restaurant.setAddress(addressMapper.mapToEntity(request.getAddress()));
    restaurant.setUserId(userId);

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

    return restaurants.stream().map(this::mapToMinimalDto).collect(Collectors.toList());
  }

  public RestaurantMinimalResponse mapToMinimalDto(Restaurant restaurant) {

    if (restaurant == null) {
      return null;
    }

    RestaurantMinimalResponse restaurantMinimalResponse = new RestaurantMinimalResponse();
    restaurantMinimalResponse.setId(restaurant.getId());
    restaurantMinimalResponse.setName(restaurant.getName());
    restaurantMinimalResponse.setCategory(new HashSet<>(restaurant.getCategories()));
    restaurantMinimalResponse.setDeliveryFee(restaurant.getDeliveryFee());
    restaurantMinimalResponse.setAverageRating(restaurant.getAverageRating());
    restaurantMinimalResponse.setTotalReviews(restaurant.getReviews().size());
    restaurantMinimalResponse.setMinimalOrder(restaurant.getMinimalOrder());

    return restaurantMinimalResponse;
  }

  public RestaurantDetailedResponse mapToDetailedDto(Restaurant restaurant) {

    if (restaurant == null) {
      return null;
    }

    RestaurantDetailedResponse restaurantDetailedResponse = new RestaurantDetailedResponse();
    restaurantDetailedResponse.setId(restaurant.getId());
    restaurantDetailedResponse.setName(restaurant.getName());
    restaurantDetailedResponse.setOpeningHours(
        restaurant.getOpeningHours().entrySet().stream().collect(
            Collectors.toMap(entry -> entry.getKey().toString(),
                entry -> intervalFormatter.formatTimeInterval(entry.getValue())))
    );
    restaurantDetailedResponse.setPickUp(restaurant.isPickUp());
    restaurantDetailedResponse.setDelivery(restaurant.isDelivery());
    restaurantDetailedResponse.setCategory(
        restaurant.getCategories().stream().map(Category::toString)
            .collect(Collectors.joining(", ")));
    restaurantDetailedResponse.setDeliveryFee(restaurant.getDeliveryFee());
    restaurantDetailedResponse.setPhoneNumber(restaurant.getPhoneNumber());
    restaurantDetailedResponse.setWebsite(restaurant.getWebsite());
    restaurantDetailedResponse.setDescription(restaurant.getDescription());
    restaurantDetailedResponse.setAverageRating(restaurant.getAverageRating());
    restaurantDetailedResponse.setTotalReviews(restaurant.getReviews().size());
    restaurantDetailedResponse.setMinimalOrder(restaurant.getMinimalOrder());
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
    restaurant.setCategories(
        request.getCategory().stream().map(Category::valueOf).collect(Collectors.toSet()));
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

  /**
   * Parse opening hours from Map<String, String> to Map<DayOfWeek, LocalTime[]> e.g. "monday":
   * "08:00-16:00" -> DayOfWeek.MONDAY: [LocalTime.of(8, 0), LocalTime.of(16, 0)]
   *
   * @param openingHours Map<String, String> with day of week as key and time interval as value
   * @return Map<DayOfWeek, LocalTime [ ]> with day of week as key and array of LocalTime as value
   */
  private Map<DayOfWeek, LocalTime[]> parseOpeningHours(Map<String, String> openingHours) {

    return openingHours.entrySet().stream().collect(
        Collectors.toMap(entry -> DayOfWeek.valueOf(entry.getKey().toUpperCase()),
            entry -> intervalFormatter.parseTimeInterval(entry.getValue())

        ));
  }
}
