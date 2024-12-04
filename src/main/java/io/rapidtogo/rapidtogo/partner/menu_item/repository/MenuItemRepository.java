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

  @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM MenuItem p WHERE p.name = :name AND p.menu.id = :menuId")
  boolean existsByNameAndMenuId(String name, Long menuId);

  @Query("SELECT p FROM MenuItem p WHERE p.id = :productId AND p.menu.id = :menuId")
  Optional<MenuItem> findByIdAndMenuId(Long productId, Long menuId);
}
