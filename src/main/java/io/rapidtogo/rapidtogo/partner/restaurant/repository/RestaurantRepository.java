package io.rapidtogo.rapidtogo.partner.restaurant.repository;

import io.rapidtogo.rapidtogo.partner.restaurant.model.Restaurant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

  @Query("SELECT r FROM Restaurant r WHERE r.userId = :userId")
  List<Restaurant> findAll(String userId);

  @Query("SELECT r FROM Restaurant r")
  List<Restaurant> findAll();

  @Query("SELECT r FROM Restaurant r WHERE r.id = :restaurantId AND r.userId = :userId")
  Optional<Restaurant> findById(Long restaurantId, String userId);

  @Query("SELECT r FROM Restaurant r WHERE r.address.city = :city AND r.userId = :userId")
  List<Restaurant> findAllByCity(String city, String userId);

  @Query("SELECT r FROM Restaurant r WHERE r.address.city = :city")
  List<Restaurant> findAllByCity(String city);

  @Query("SELECT CASE WHEN COUNT(r) > 0 THEN TRUE ELSE FALSE END FROM Restaurant r WHERE r.id = :restaurantId AND r.userId = :userId")
  boolean existsById(Long restaurantId, String userId);
}
