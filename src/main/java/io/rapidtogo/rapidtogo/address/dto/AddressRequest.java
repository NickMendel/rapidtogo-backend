package io.rapidtogo.rapidtogo.address.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressRequest {

  @NotBlank(message = "Street is required.")
  private String street;

  @NotBlank(message = "House number is required.")
  private String houseNumber;

  @NotBlank(message = "City is required.")
  private String city;

  @NotBlank(message = "Zip code is required.")
  private String zipCode;

  @NotBlank(message = "Country ISO Code is required.")
  @Size(min = 2, max = 2, message = "Country ISO code must be 2 characters long.")
  private String countryISOCode;
}
