package com.mpajak99.stripinvoices.controller;

import com.mpajak99.stripinvoices.service.CustomerService;
import com.mpajak99.stripinvoices.model.CustomerData;
import com.stripe.model.Customer;
import com.stripe.exception.StripeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/costumers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() throws StripeException{
        if(customerService.getAllCustomers().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody CustomerData customerData) throws StripeException {
        Customer addedCustomer = customerService.addCustomer(customerData);

        return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);
    }
}
