package io.rapidtogo.rapidtogo.customer.order_item.model;

import io.rapidtogo.rapidtogo.customer.order.model.Order;
import io.rapidtogo.rapidtogo.partner.menu_item.model.MenuItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_seq")
  @SequenceGenerator(name = "order_item_id_seq", sequenceName = "order_item_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "price", nullable = false)
  private BigDecimal price;

  @Column(name = "quantity", nullable = false)
  private int quantity;

  @Column(name = "total", nullable = false)
  private BigDecimal total;

  @Column(name = "notes", length = 1000)
  private String notes;

  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE,
      CascadeType.REFRESH,
      CascadeType.DETACH
  }, fetch = FetchType.LAZY)
  private Order order;

  @ManyToOne(fetch = FetchType.LAZY)
  private MenuItem menuItem;

  @Column(name = "user_id", nullable = false)
  private String userId;
}
