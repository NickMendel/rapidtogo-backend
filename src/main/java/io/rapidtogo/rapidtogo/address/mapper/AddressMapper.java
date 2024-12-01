package io.rapidtogo.rapidtogo.address.mapper;

import io.rapidtogo.rapidtogo.address.dto.AddressRequest;
import io.rapidtogo.rapidtogo.address.dto.AddressResponse;
import io.rapidtogo.rapidtogo.address.model.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

  public Address mapToEntity(AddressRequest addressCreateRequest) {

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

  public AddressResponse mapToDto(Address address) {

    if (address == null) {
      return null;
    }

    AddressResponse addressResponse = new AddressResponse();
    addressResponse.setId(address.getId());
    addressResponse.setStreet(address.getStreet());
    addressResponse.setHouseNumber(address.getHouseNumber());
    addressResponse.setCity(address.getCity());
    addressResponse.setZipCode(address.getZipCode());
    addressResponse.setCountry(address.getCountry());

    return addressResponse;
  }

  public Address updateEntity(AddressRequest request, Address address) {

    if (request == null) {
      return address;
    }

    address.setStreet(request.getStreet());
    address.setHouseNumber(request.getHouseNumber());
    address.setCity(request.getCity());
    address.setZipCode(request.getZipCode());
    address.setCountry(request.getCountry());

    return address;
  }
}
