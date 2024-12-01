package io.rapidtogo.rapidtogo.review.controller;

import io.rapidtogo.rapidtogo.review.dto.ReviewResponse;
import io.rapidtogo.rapidtogo.review.service.ReviewService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ReviewRelatedController {

  private final ReviewService reviewService;

  @GetMapping("/restaurants/{restaurantId}/reviews")
  public ResponseEntity<List<ReviewResponse>> getAllReviewsByRestaurantId(
      @PathVariable Long restaurantId) {

    List<ReviewResponse> reviews = reviewService.getAllReviewsByRestaurantId(restaurantId);
    log.info("Found {} reviews for restaurant with id: {}", reviews.size(), restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(reviews);
  }
}
