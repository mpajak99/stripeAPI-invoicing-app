package com.mpajak99.stripinvoices.service;

import com.mpajak99.stripinvoices.model.CustomerData;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.param.CustomerCreateParams;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    private String key = "sk_test_51Jb7MWAuK0ljMfv73q52eFrZimzW2TbcxxwrpDjrCnI18R0CI6m2u1fNJ2NjdnnHCXsyAyuF5leaZeSEDHrBVtYX0006DzRNFV";

    public List<Customer> getAllCustomers() throws StripeException {
        Stripe.apiKey = key;

        List<Customer> customers = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 100);

        CustomerCollection collection = Customer.list(params);
        customers = collection.getData();

        return customers;
    }

    public Customer addCustomer(CustomerData customerData) throws StripeException {
        Stripe.apiKey = key;

        CustomerCreateParams params = CustomerCreateParams.builder()
                .setName(customerData.getName())
                .setEmail(customerData.getEmail())
                .setDescription(customerData.getDescription())
                .setAddress(CustomerCreateParams.Address.builder()
                        .setCountry(customerData.getCountry())
                        .setCity(customerData.getCity())
                        .setPostalCode(customerData.getPostalCode())
                        .setLine1(customerData.getStreet())
                        .build())
                .build();

        Customer customer = Customer.create(params);
        return customer;
    }
}

