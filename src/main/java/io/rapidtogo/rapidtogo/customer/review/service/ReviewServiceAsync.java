package io.rapidtogo.rapidtogo.customer.review.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewServiceAsync {

  private final ReviewService reviewService;

  /**
   * Recalculates average rating for a restaurant asynchronously.
   *
   * @param restaurantId the id of the restaurant for which the average rating should be
   *                     recalculated
   */
  @Async
  public void recalculateRestaurantAverageRatingAsync(Long restaurantId) {

    log.info("Recalculating average rating for restaurant with id: {}", restaurantId);
    reviewService.recalculateRestaurantAverageRating(restaurantId);
    log.info("Average rating recalculated successfully for restaurant with id: {}", restaurantId);
  }
}
