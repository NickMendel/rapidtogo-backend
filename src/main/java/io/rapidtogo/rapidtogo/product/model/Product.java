package io.rapidtogo.rapidtogo.product.model;

import io.rapidtogo.rapidtogo.menu.model.Menu;
import io.rapidtogo.rapidtogo.product.enums.Allergy;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
  @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "description", nullable = false, length = 1000)
  private String description;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @ElementCollection
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "product_allergies", joinColumns = @JoinColumn(name = "product_id"))
  @Column(name = "allergy")
  private Set<Allergy> allergies;

  @ManyToOne(fetch = FetchType.LAZY)
  private Menu menu;
}
