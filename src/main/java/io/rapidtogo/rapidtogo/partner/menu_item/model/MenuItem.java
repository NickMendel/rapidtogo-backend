package io.rapidtogo.rapidtogo.partner.menu_item.model;

import io.rapidtogo.rapidtogo.customer.order_item.model.OrderItem;
import io.rapidtogo.rapidtogo.partner.menu.model.Menu;
import io.rapidtogo.rapidtogo.partner.menu_item.enums.Allergy;
import jakarta.persistence.CascadeType;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_item")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_item_id_seq")
  @SequenceGenerator(name = "menu_item_id_seq", sequenceName = "menu_item_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name", nullable = false, length = 100)
  private String name;

  @Column(name = "description", nullable = false, length = 1000)
  private String description;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "active", nullable = false)
  private boolean active = true;

  @ElementCollection
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "menu_item_allergies", joinColumns = @JoinColumn(name = "menu_item_id"))
  @Column(name = "allergy")
  private Set<Allergy> allergies;

  @OneToMany(mappedBy = "menuItem", cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE,
      CascadeType.REFRESH,
      CascadeType.DETACH
  }, fetch = FetchType.LAZY)
  private List<OrderItem> orderItems;

  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE,
      CascadeType.REFRESH,
      CascadeType.DETACH
  }, fetch = FetchType.LAZY)
  private Menu menu;
}
