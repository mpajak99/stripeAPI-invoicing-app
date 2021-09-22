package com.mpajak99.stripinvoices.service;

import com.mpajak99.stripinvoices.model.CustomerData;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.BDDMockito.given;

public class CustomerServiceTest {

//    Unfortunately, my ideas so far do not work -> TODO

    @Autowired
    private CustomerService underTest = new CustomerService();

//    @Test
//    void addCustomer_AllParametersOK_customerAdded() throws StripeException {
//        //given
//        Customer expected = underTest.addCustomer(CustomerData.builder()
//                .name("Customer")
//                .email("email@email.pl")
//                .country("Poland")
//                .city("Gdańsk")
//                .postalCode("12-345")
//                .street("Street")
//                .build());
//        Customer customer = new Customer();
//
//        //when
//        MockedStatic<Customer> customerMockedStatic = Mockito.mockStatic(Customer.class);
//        customerMockedStatic.when(() -> Customer.create(anyMap(), any())).thenReturn(customer);
//        given(Customer.create(anyMap(), any())).willReturn(customer);
//
//        //then
//        assertEquals(customer, expected);
//    }
}
