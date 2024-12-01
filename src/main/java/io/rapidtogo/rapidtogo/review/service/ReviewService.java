package io.rapidtogo.rapidtogo.review.service;

import io.rapidtogo.rapidtogo.restaurant.model.Restaurant;
import io.rapidtogo.rapidtogo.restaurant.repository.RestaurantRepository;
import io.rapidtogo.rapidtogo.restaurant.repository.RestaurantRepositoryHelper;
import io.rapidtogo.rapidtogo.review.dto.ReviewRequest;
import io.rapidtogo.rapidtogo.review.dto.ReviewResponse;
import io.rapidtogo.rapidtogo.review.exception.ReviewNotUpdatableException;
import io.rapidtogo.rapidtogo.review.mapper.ReviewMapper;
import io.rapidtogo.rapidtogo.review.model.Review;
import io.rapidtogo.rapidtogo.review.repository.ReviewRepository;
import io.rapidtogo.rapidtogo.review.repository.ReviewRepositoryHelper;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReviewService {

  private final ReviewRepositoryHelper reviewRepositoryHelper;
  private final ReviewRepository reviewRepository;
  private final RestaurantRepository restaurantRepository;
  private final ReviewMapper reviewMapper;
  private final RestaurantRepositoryHelper restaurantRepositoryHelper;

  @Transactional(readOnly = true)
  public List<ReviewResponse> getAllReviewsByRestaurantId(Long restaurantId) {

    return reviewMapper.mapToListDto(reviewRepositoryHelper.findAllByRestaurantId(restaurantId));
  }

  @Transactional
  public String createReview(Long restaurantId, ReviewRequest request) {

    Restaurant restaurant = restaurantRepositoryHelper.findById(restaurantId);
    Review review = reviewMapper.mapToEntity(request);
    restaurant.getReviews().add(review);
    review.setRestaurant(restaurant);

    reviewRepository.save(review);

    return "Review created successfully";
  }

  @Transactional
  public String updateReview(Long restaurantId, Long reviewId, ReviewRequest request) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    Review review = reviewRepositoryHelper.findByIdAndByRestaurantId(reviewId, restaurantId);

    if (review.getCreatedAt().plusMinutes(30).isBefore(LocalDateTime.now(ZoneOffset.UTC))) {
      throw new ReviewNotUpdatableException(
          "Review can be updated only within 30 minutes after creation.");
    }

    review = reviewMapper.updateEntity(request, review);
    reviewRepository.save(review);

    return "Review with id: " + reviewId + " of restaurant with id: " + restaurantId
        + " updated successfully";
  }

  @Transactional
  public String deleteReview(Long restaurantId, Long reviewId) {

    restaurantRepositoryHelper.checkExistence(restaurantId);
    Review review = reviewRepositoryHelper.findByIdAndByRestaurantId(reviewId, restaurantId);

    if (review.getCreatedAt().plusMinutes(30).isBefore(LocalDateTime.now(ZoneOffset.UTC))) {
      throw new ReviewNotUpdatableException(
          "Review can be deleted only within 30 minutes after creation.");
    }

    reviewRepository.delete(review);

    return "Review with id: " + reviewId + " of restaurant with id: " + restaurantId
        + " deleted successfully";
  }

  /**
   * Recalculates the average rating of a restaurant based on the reviews.
   *
   * @param restaurantId the id of the restaurant
   */
  @Transactional
  public void recalculateRestaurantAverageRating(Long restaurantId) {

    Restaurant restaurant = restaurantRepositoryHelper.findById(restaurantId);
    List<Review> reviews = reviewRepositoryHelper.findAllByRestaurantId(restaurantId);

    int totalQualityRate = reviews.stream().mapToInt(Review::getQualityRate).sum();
    int totalDeliveryRate = reviews.stream().mapToInt(Review::getDeliveryRate).sum();
    double totalRate = (totalDeliveryRate + totalQualityRate) / 2.0;
    int totalReviews = reviews.size();
    Double averageRating = totalRate / totalReviews;

    restaurant.setAverageRating(averageRating);
    restaurantRepository.save(restaurant);
  }
}
