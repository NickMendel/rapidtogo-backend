package io.rapidtogo.rapidtogo.restaurant.repository;

import io.rapidtogo.rapidtogo.restaurant.model.Restaurant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

  Optional<Restaurant> findById(Long restaurantId);
}
