package io.rapidtogo.rapidtogo.customer.review.repository;

import io.rapidtogo.rapidtogo.customer.review.model.Review;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

  @Query("SELECT r FROM Review r WHERE r.restaurant.id = :restaurantId")
  List<Review> findAllByRestaurantId(Long restaurantId);

  @Query("SELECT r FROM Review r WHERE r.id = :reviewId AND r.restaurant.id = :restaurantId")
  Optional<Review> findByIdAndByRestaurantId(Long reviewId, Long restaurantId);
}
