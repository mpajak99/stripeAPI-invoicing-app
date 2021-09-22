package com.mpajak99.stripinvoices.service;

import com.mpajak99.stripinvoices.model.CustomerData;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.CustomerCollection;
import com.stripe.param.CustomerCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }


    public List<Customer> getAllCustomers() throws StripeException {
        List<Customer> customers = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("limit", 100);

        CustomerCollection collection = Customer.list(params);
        customers = collection.getData();

        return customers;
    }

    public Customer addCustomer(CustomerData customerData) throws StripeException {
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

