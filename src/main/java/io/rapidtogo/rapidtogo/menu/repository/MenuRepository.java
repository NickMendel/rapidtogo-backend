package io.rapidtogo.rapidtogo.menu.repository;

import io.rapidtogo.rapidtogo.menu.model.Menu;
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

  @Query("SELECT m FROM Menu m WHERE m.restaurant.id = :restaurantId AND m.id = :menuId")
  Optional<Menu> findByRestaurantIdAndById(Long restaurantId, Long menuId);
}
