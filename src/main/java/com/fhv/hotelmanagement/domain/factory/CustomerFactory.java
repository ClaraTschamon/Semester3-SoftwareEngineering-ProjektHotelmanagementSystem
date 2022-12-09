//Hotelmanagementsystem TeamA 2022/23
package com.fhv.hotelmanagement.domain.factory;

import com.fhv.hotelmanagement.domain.domainModel.Booking;
import com.fhv.hotelmanagement.domain.domainModel.Customer;
import com.fhv.hotelmanagement.domain.domainModel.Reservation;
import com.fhv.hotelmanagement.domain.exceptions.CustomerIsInvalidException;
import com.fhv.hotelmanagement.persistence.PersistenceFacade;
import com.fhv.hotelmanagement.services.StringValidator;
import com.fhv.hotelmanagement.view.DTOs.AddressDTO;
import com.fhv.hotelmanagement.view.DTOs.BookingDTO;
import com.fhv.hotelmanagement.view.DTOs.CustomerDTO;
import com.fhv.hotelmanagement.view.DTOs.ReservationDTO;

import java.time.LocalDate;
import java.util.ArrayList;

public class CustomerFactory {

    public static ArrayList<CustomerDTO> getSavedCustomers() {
        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : PersistenceFacade.getSavedCustomers()) {
            customerDTOS.add(createCustomerDTO(customer));
        }
        return customerDTOS;
    }

    public static Long saveCustomer(CustomerDTO customerDTO) throws CustomerIsInvalidException {
        Long customerNumber = customerDTO.getNumber();
        if (checkCustomer(customerDTO)) {
            Customer customer = createCustomer(customerDTO);

            if (customerNumber == null) {
                customerNumber = PersistenceFacade.insertCustomer(customer);
            } else {
                PersistenceFacade.storeCustomer(customer);
            }
        } else {
            throw new CustomerIsInvalidException();
        }
        return customerNumber;
    }

    protected static CustomerDTO createCustomerDTO(Customer customer) {

        return new CustomerDTO (customer.getNumber(), customer.getFirstName(), customer.getLastName(),
                customer.getDateOfBirth(), customer.getNationality(), customer.getPhoneNumber(), customer.getEmail(),
                AddressFactory.createAddressDTO(customer.getAddress()), customer.getSaved());
    }

    protected static Customer createCustomer(CustomerDTO customerDTO) {
        AddressDTO address = customerDTO.getAddress();

        ArrayList<Booking> bookings = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();

        Customer customer = new Customer (customerDTO.getNumber(), customerDTO.getFirstName(), customerDTO.getLastName(),
                customerDTO.getDateOfBirth(), customerDTO.getNationality(), customerDTO.getPhoneNumber(), customerDTO.getEmail(),
                address.getStreet(), address.getHouseNumber(), address.getPostalCode(), address.getCity(), address.getCountry(),
                customerDTO.getSaved(), bookings, reservations);


        return customer;
    }

    protected static boolean checkCustomer(CustomerDTO customerDTO) {
        return (customerDTO != null) &&
                (StringValidator.checkString(customerDTO.getFirstName())) &&
                (StringValidator.checkString(customerDTO.getLastName())) &&
                (customerDTO.getDateOfBirth() != null && customerDTO.getDateOfBirth().isBefore(LocalDate.now())) &&
                (StringValidator.checkString(customerDTO.getNationality())) &&
                (StringValidator.checkString(customerDTO.getPhoneNumber()) && StringValidator.checkValidPhoneNumber(customerDTO.getPhoneNumber())) &&
                (StringValidator.checkString(customerDTO.getEmail()) && StringValidator.checkValidEmail(customerDTO.getEmail())) &&
                (AddressFactory.checkAddress(customerDTO.getAddress())) &&
                (customerDTO.getSaved() != null);
    }
}
