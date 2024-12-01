package io.rapidtogo.rapidtogo.restaurant.model;

import io.rapidtogo.rapidtogo.address.model.Address;
import io.rapidtogo.rapidtogo.menu.model.Menu;
import io.rapidtogo.rapidtogo.restaurant.enums.Category;
import io.rapidtogo.rapidtogo.review.model.Review;
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
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.MapKeyEnumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
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

  @ElementCollection
  @MapKeyEnumerated(EnumType.STRING)
  @MapKeyColumn(name = "day_of_week")
  @CollectionTable(name = "restaurant_opening_hours", joinColumns = @JoinColumn(name = "restaurant_id"))
  @Column(name = "time_interval", columnDefinition = "VARCHAR")
  private Map<DayOfWeek, LocalTime[]> openingHours;

  @Column(name = "pick_up", nullable = false)
  private boolean pickUp = false;

  @Column(name = "delivery", nullable = false)
  private boolean delivery = false;

  @Column(name = "delivery_fee")
  private BigDecimal deliveryFee = BigDecimal.ZERO;

  @Column(name = "minimal_order")
  private BigDecimal minimalOrder = BigDecimal.ZERO;

  @ElementCollection(targetClass = Category.class)
  @Enumerated(EnumType.STRING)
  @CollectionTable(name = "restaurant_category", joinColumns = @JoinColumn(name = "restaurant_id"))
  @Column(name = "category", nullable = false)
  private Set<Category> categories;

  @Column(name = "average_rating")
  private Double averageRating;

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

  // TODO: Add nullable = false after implementing user registration/login
  @Column(name = "user_id")
  private Long userId;

  @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
  private Address address;

  @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Review> reviews = new ArrayList<>();

  @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Menu> menus = new ArrayList<>();
}
