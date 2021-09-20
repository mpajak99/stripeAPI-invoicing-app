package com.mpajak99.stripinvoices.controller;

import com.mpajak99.stripinvoices.service.InvoiceService;
import com.mpajak99.stripinvoices.model.InvoiceData;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<Invoice> getInvoiceById (@PathVariable("id") String id) throws StripeException {
        try {
            invoiceService.getInvoiceById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(invoiceService.getInvoiceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Invoice> addInvoice (@Valid @RequestBody InvoiceData invoiceData) throws StripeException {
        Invoice addedInvoice = invoiceService.addInvoice(invoiceData);

        return new ResponseEntity<>(addedInvoice, HttpStatus.CREATED);
    }
}
