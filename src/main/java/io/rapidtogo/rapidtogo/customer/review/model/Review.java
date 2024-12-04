package io.rapidtogo.rapidtogo.customer.review.model;

import io.rapidtogo.rapidtogo.partner.restaurant.model.Restaurant;
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
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "review")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

  @Column(name = "created_at", nullable = false)
  private final LocalDateTime createdAt = LocalDateTime.now(ZoneOffset.UTC);

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_id_seq")
  @SequenceGenerator(name = "review_id_seq", sequenceName = "review_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "content")
  private String content;

  @Column(name = "quality_score", nullable = false)
  private int qualityRate;

  @Column(name = "delivery_score")
  private int deliveryRate;

  @ManyToOne(cascade = {
      CascadeType.PERSIST,
      CascadeType.MERGE,
      CascadeType.REFRESH,
      CascadeType.DETACH
  }, fetch = FetchType.LAZY)
  private Restaurant restaurant;

  // TODO: Add nullable = false after implementing user authentication
  @Column(name = "user_id")
  private String userId;
}
