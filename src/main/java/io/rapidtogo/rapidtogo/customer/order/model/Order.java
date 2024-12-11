package io.rapidtogo.rapidtogo.customer.order.model;

import io.rapidtogo.rapidtogo.address.model.Address;
import io.rapidtogo.rapidtogo.customer.order.enums.OrderStatus;
import io.rapidtogo.rapidtogo.customer.order_item.model.OrderItem;
import io.rapidtogo.rapidtogo.partner.restaurant.model.Restaurant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

  @Column(name = "created_at", nullable = false)
  private final LocalDateTime createdAt = LocalDateTime.now(ZoneOffset.UTC);

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_seq")
  @SequenceGenerator(name = "order_id_seq", sequenceName = "order_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private OrderStatus status = OrderStatus.PENDING;

  @Column(name = "total", nullable = false)
  private BigDecimal total;

  @Column(name = "user_id", nullable = false)
  private String userId;

  @Column(name = "delivery", nullable = false)
  private boolean delivery = false;

  @Column(name = "take_away", nullable = false)
  private boolean takeAway = false;

  @Column(name = "delivery_fee")
  private BigDecimal deliveryFee = BigDecimal.ZERO;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  private Address deliveryAddress;

  @Column(name = "is_paid", nullable = false)
  private boolean isPaid = false;

  @Column(name = "notes", length = 1000)
  private String notes;

  @Column(name = "received_at")
  private LocalDateTime receivedAt;

  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE,
      CascadeType.REFRESH,
      CascadeType.DETACH
  }, fetch = FetchType.LAZY)
  private Restaurant restaurant;

  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<OrderItem> orderItems;
}
