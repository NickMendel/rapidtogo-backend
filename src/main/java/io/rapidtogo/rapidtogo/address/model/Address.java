package io.rapidtogo.rapidtogo.address.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_id_seq")
  @SequenceGenerator(name = "address_id_seq", sequenceName = "address_id_seq", allocationSize = 1)
  private Long id;

  @Column(name = "street", nullable = false)
  private String street;

  @Column(name = "house_number", nullable = false)
  private String houseNumber;

  @Column(name = "city", nullable = false)
  private String city;

  @Column(name = "zip_code", nullable = false)
  private String zipCode;

  @Column(name = "country_iso_code", length = 2, nullable = false)
  private String countryISOCode;
}
