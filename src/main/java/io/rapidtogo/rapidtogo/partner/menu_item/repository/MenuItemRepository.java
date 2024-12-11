package io.rapidtogo.rapidtogo.partner.menu_item.repository;

import io.rapidtogo.rapidtogo.partner.menu_item.model.MenuItem;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

  @Query("SELECT CASE WHEN COUNT(mi) > 0 THEN TRUE ELSE FALSE END FROM MenuItem mi "
      + "WHERE mi.name = :name AND mi.menu.id = :menuId AND mi.userId = :userId")
  boolean existsByNameAndMenuId(String name, Long menuId, String userId);

  @Query("SELECT mi FROM MenuItem mi WHERE mi.id = :menuItemId AND mi.menu.id = :menuId")
  Optional<MenuItem> findByIdAndMenuId(Long menuItemId, Long menuId);

  @Query("SELECT mi FROM MenuItem mi WHERE mi.id = :menuItemId AND mi.menu.id = :menuId AND mi.userId = :userId")
  Optional<MenuItem> findByIdAndMenuId(Long menuItemId, Long menuId, String userId);

  @Query("SELECT mi FROM MenuItem mi WHERE mi.id = :menuItemId")
  Optional<MenuItem> findById(Long menuItemId);

  @Query("SELECT mi FROM MenuItem mi WHERE mi.id = :menuItemId AND mi.userId = :userId")
  Optional<MenuItem> findById(Long menuItemId, String userId);
}
