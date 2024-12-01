package io.rapidtogo.rapidtogo.product.repository;

import io.rapidtogo.rapidtogo.product.model.Product;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM Product p WHERE p.name = :name AND p.menu.id = :menuId")
  boolean existsByNameAndMenuId(String name, Long menuId);

  @Query("SELECT p FROM Product p WHERE p.id = :productId AND p.menu.id = :menuId")
  Optional<Product> findByIdAndMenuId(Long productId, Long menuId);
}
