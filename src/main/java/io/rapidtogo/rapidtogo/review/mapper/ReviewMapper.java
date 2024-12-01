package io.rapidtogo.rapidtogo.review.mapper;

import io.rapidtogo.rapidtogo.review.dto.ReviewRequest;
import io.rapidtogo.rapidtogo.review.dto.ReviewResponse;
import io.rapidtogo.rapidtogo.review.model.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

  public Review mapToEntity(ReviewRequest reviewRequest) {

    if (reviewRequest == null) {
      return null;
    }

    Review review = new Review();
    review.setContent(reviewRequest.getContent());
    review.setQualityRate(reviewRequest.getQualityRate());
    review.setDeliveryRate(reviewRequest.getDeliveryRate());

    return review;
  }

  public ReviewResponse mapToDto(Review review) {

    if (review == null) {
      return null;
    }

    ReviewResponse reviewResponse = new ReviewResponse();
    reviewResponse.setId(review.getId());
    reviewResponse.setContent(review.getContent());
    reviewResponse.setQualityRate(review.getQualityRate());
    reviewResponse.setDeliveryRate(review.getDeliveryRate());

    return reviewResponse;
  }

  public List<ReviewResponse> mapToListDto(List<Review> reviews) {

    if (reviews == null || reviews.isEmpty()) {
      return new ArrayList<>();
    }

    return reviews.stream().map(this::mapToDto).collect(Collectors.toList());
  }

  public Review updateEntity(ReviewRequest reviewRequest, Review review) {

    if (reviewRequest == null) {
      return review;
    }

    review.setContent(reviewRequest.getContent());
    review.setQualityRate(reviewRequest.getQualityRate());
    review.setDeliveryRate(reviewRequest.getDeliveryRate());

    return review;
  }
}
