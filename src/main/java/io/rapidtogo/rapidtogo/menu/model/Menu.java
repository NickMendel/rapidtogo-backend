package io.rapidtogo.rapidtogo.menu.model;

import io.rapidtogo.rapidtogo.product.model.Product;
import io.rapidtogo.rapidtogo.restaurant.model.Restaurant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_id_seq")
  @SequenceGenerator(name = "menu_id_seq", sequenceName = "menu_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "description")
  private String description;

  @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Product> products = new ArrayList<>();

  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE,
      CascadeType.REFRESH,
      CascadeType.DETACH
  }, fetch = FetchType.LAZY)
  private Restaurant restaurant;

  @Column(name = "user_id")
  private String userId;
}
