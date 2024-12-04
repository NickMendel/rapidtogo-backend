package io.rapidtogo.rapidtogo.customer.review.repository;

import io.rapidtogo.rapidtogo.customer.review.model.Review;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewRepositoryHelper {

  private final ReviewRepository reviewRepository;

  /**
   * Find all reviews by restaurant id.
   *
   * @param restaurantId the restaurant id
   * @return the list of reviews
   * @throws EntityNotFoundException if no reviews found for the restaurant
   */
  public List<Review> findAllByRestaurantId(Long restaurantId) {

    List<Review> reviews = reviewRepository.findAllByRestaurantId(restaurantId);

    if (reviews.isEmpty()) {
      throw new EntityNotFoundException("No reviews found for restaurant with id: " + restaurantId);
    }

    return reviews;
  }

  /**
   * Find review by id and restaurant id.
   *
   * @param reviewId     the review id
   * @param restaurantId the restaurant id
   * @return the review
   * @throws EntityNotFoundException if review not found for the restaurant with the given id
   */
  public Review findByIdAndByRestaurantId(Long reviewId, Long restaurantId) {

    return reviewRepository.findByIdAndByRestaurantId(reviewId, restaurantId)
        .orElseThrow(
            () -> new EntityNotFoundException("Review with id: " + reviewId + " not found"));
  }
}
