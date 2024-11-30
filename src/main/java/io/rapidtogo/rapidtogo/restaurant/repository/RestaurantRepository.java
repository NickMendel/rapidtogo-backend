package io.rapidtogo.rapidtogo.restaurant.repository;

import io.rapidtogo.rapidtogo.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
