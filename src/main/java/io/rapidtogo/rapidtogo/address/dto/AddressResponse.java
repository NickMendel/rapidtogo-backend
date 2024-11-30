package io.rapidtogo.rapidtogo.address.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse {

  private Long id;
  private String street;
  private String houseNumber;
  private String city;
  private String zipCode;
  private String country;
}
