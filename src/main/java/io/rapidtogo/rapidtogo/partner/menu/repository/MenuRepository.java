package io.rapidtogo.rapidtogo.partner.menu.repository;

import io.rapidtogo.rapidtogo.partner.menu.model.Menu;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

  @Query("SELECT m FROM Menu m WHERE m.restaurant.id = :restaurantId")
  List<Menu> findAllByRestaurantId(Long restaurantId);

  @Query("SELECT m FROM Menu m WHERE m.restaurant.id = :restaurantId AND m.userId = :userId")
  List<Menu> findAllByRestaurantId(Long restaurantId, String userId);

  @Query("SELECT m FROM Menu m WHERE m.restaurant.id = :restaurantId AND m.id = :menuId AND m.userId = :userId")
  Optional<Menu> findByRestaurantIdAndById(Long restaurantId, Long menuId, String userId);

  @Query("SELECT m FROM Menu m WHERE m.restaurant.id = :restaurantId AND m.id = :menuId")
  Optional<Menu> findByRestaurantIdAndById(Long restaurantId, Long menuId);

  @Query("SELECT m FROM Menu m WHERE m.id = :menuId AND m.userId = :userId")
  Optional<Menu> findById(Long menuId, String userId);

  @Query("SELECT CASE WHEN COUNT(m) > 0 THEN TRUE ELSE FALSE END FROM Menu m WHERE m.id = :menuId AND m.userId = :userId")
  boolean existsById(Long menuId, String userId);
}
