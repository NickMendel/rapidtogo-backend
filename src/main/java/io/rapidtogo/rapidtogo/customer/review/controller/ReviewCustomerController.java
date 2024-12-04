package io.rapidtogo.rapidtogo.customer.review.controller;

import io.rapidtogo.rapidtogo.customer.review.dto.ReviewRequest;
import io.rapidtogo.rapidtogo.customer.review.service.ReviewService;
import io.rapidtogo.rapidtogo.customer.review.service.ReviewServiceAsync;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ReviewCustomerController {

  private final ReviewService reviewService;
  private final ReviewServiceAsync reviewServiceAsync;

  @PostMapping("/restaurants/{restaurantId}/reviews")
  public ResponseEntity<String> createReview(@PathVariable Long restaurantId,
      @RequestBody @Valid ReviewRequest request) {

    String successMessage = reviewService.createReview(restaurantId, request);
    log.info(successMessage);

    reviewServiceAsync.recalculateRestaurantAverageRatingAsync(restaurantId);

    return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
  }

  @PutMapping("/restaurants/{restaurantId}/reviews/{reviewId}")
  public ResponseEntity<String> updateReview(@PathVariable Long restaurantId,
      @PathVariable Long reviewId, @RequestBody @Valid ReviewRequest request) {

    String successMessage = reviewService.updateReview(restaurantId, reviewId, request);
    log.info(successMessage);

    reviewServiceAsync.recalculateRestaurantAverageRatingAsync(restaurantId);

    return ResponseEntity.status(HttpStatus.OK).body(successMessage);
  }

  @DeleteMapping("/restaurants/{restaurantId}/reviews/{reviewId}")
  public ResponseEntity<String> deleteReview(@PathVariable Long restaurantId,
      @PathVariable Long reviewId) {

    String successMessage = reviewService.deleteReview(restaurantId, reviewId);
    log.info(successMessage);

    reviewServiceAsync.recalculateRestaurantAverageRatingAsync(restaurantId);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(successMessage);
  }
}
