package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.Address;
import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.view.DTOs.AddressDTO;

public class AddressFactory {
    protected static AddressDTO createAddressDTO(Address address) {
        return new AddressDTO(address.getStreet(), address.getHouseNumber(), address.getPostalCode(),
                address.getCity(), address.getCountry());
    }

    protected static boolean checkAddress(AddressDTO addressDTO) {
        return (addressDTO != null) &&
                (StringValidator.checkString(addressDTO.getStreet())) &&
                (StringValidator.checkString(addressDTO.getHouseNumber())) &&
                (StringValidator.checkString(addressDTO.getCity())) &&
                (StringValidator.checkString(addressDTO.getPostalCode())) &&
                (StringValidator.checkString(addressDTO.getCountry()));
    }
}
