package io.rapidtogo.rapidtogo.address.mapper;

import io.rapidtogo.rapidtogo.address.dto.AddressCreateRequest;
import io.rapidtogo.rapidtogo.address.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

  public Address mapToEntity(AddressCreateRequest addressCreateRequest) {

    if (addressCreateRequest == null) {
      return null;
    }

    Address address = new Address();
    address.setStreet(addressCreateRequest.getStreet());
    address.setHouseNumber(addressCreateRequest.getHouseNumber());
    address.setCity(addressCreateRequest.getCity());
    address.setZipCode(addressCreateRequest.getZipCode());
    address.setCountry(addressCreateRequest.getCountry());

    return address;
  }
}
