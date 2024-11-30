package io.rapidtogo.rapidtogo.restaurant.model;

import io.rapidtogo.rapidtogo.address.model.Address;
import io.rapidtogo.rapidtogo.restaurant.enums.Category;
import io.rapidtogo.rapidtogo.review.model.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurant")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_id_seq")
  @SequenceGenerator(name = "restaurant_id_seq", sequenceName = "restaurant_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "opening_time", nullable = false)
  private LocalTime openingTime;

  @Column(name = "closing_time", nullable = false)
  private LocalTime closingTime;

  @Column(name = "pick_up", nullable = false)
  private boolean pickUp = false;

  @Column(name = "delivery", nullable = false)
  private boolean delivery = false;

  @Column(name = "delivery_fee")
  private BigDecimal deliveryFee = BigDecimal.ZERO;

  @Column(name = "minimal_order")
  private BigDecimal minimalOrder = BigDecimal.ZERO;

  @Column(name = "score", nullable = false)
  private BigDecimal score = BigDecimal.ZERO;

  @Column(name = "category", nullable = false)
  @Enumerated(EnumType.STRING)
  private Category category;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "website")
  private String website;

  @Column(name = "email")
  private String email;

  @Column(name = "description")
  private String description;

  @Column(name = "active", nullable = false)
  private boolean active = true;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
  private Address address;

  @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Review> reviews = new ArrayList<>();

  public int getReviewCount() {
    return reviews.size();
  }

}
