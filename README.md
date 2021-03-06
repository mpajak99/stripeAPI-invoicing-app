# StripeAPI Invoicing App

## About :bulb: 

This Spring Boot application uses Strpe API what gives the user capabilities of managing invoices in the Stripe payment system.

## Postman testing :white_check_mark:

- CustomerController

```java
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
   ```
   <strong>Adding customer: POST request</strong> at localhost:8080/customers
   [<img src="/src/main/resources/images/post-request-customer.png" alt="Post request customer"/>](post-request-customer.png)

   <strong>Getting all customers: GET request</strong> at localhost:8080/customers
   [<img src="/src/main/resources/images/get-request-customer.png" alt="Get request customer"/>](get-request-customer.png)

 - InvoiceController

```java
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
   ```
   <strong>Adding invoice: POST request</strong> at localhost:8080/invoices
   [<img src="/src/main/resources/images/post-request-invoice.png" alt="Post request invoice"/>](post-request-invoice.png)
   
   <strong>Getting invoice by its id: GET request</strong> at localhost:8080/invoices
   [<img src="/src/main/resources/images/get-request-invoice.png" alt="Get request invoice"/>](get-request-invoice.png)
