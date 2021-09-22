package com.mpajak99.stripinvoices.service;

import com.mpajak99.stripinvoices.model.InvoiceData;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Invoice;
import com.stripe.model.InvoiceItem;
import com.stripe.model.Price;
import com.stripe.model.Product;
import com.stripe.param.InvoiceCreateParams;
import com.stripe.param.InvoiceItemCreateParams;
import com.stripe.param.PriceCreateParams;
import com.stripe.param.ProductCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class InvoiceService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Invoice getInvoiceById(String id) throws StripeException {
        Invoice invoice = Invoice.retrieve(id);

        return invoice;
    }

    public Invoice addInvoice(InvoiceData invoiceData) throws StripeException {
        String customerId = invoiceData.getCustomer();

        BigDecimal quantity = invoiceData.getQuantity();

        InvoiceItemCreateParams invoiceItemParams =
                InvoiceItemCreateParams.builder()
                        .setCustomer(customerId)
                        .setPrice(addPrice(invoiceData).getId())
                        .setQuantity(quantity.longValue())
                        .setDescription(invoiceData.getDescription())
                        .build();

        InvoiceItem.create(invoiceItemParams);

        InvoiceCreateParams invoiceParams = InvoiceCreateParams.builder()
                .setCustomer(invoiceData.getCustomer())
                .setAutoAdvance(true)
                .build();

        Invoice invoice = Invoice.create(invoiceParams);

        return invoice;
    }

    private Price addPrice(InvoiceData invoiceData) throws StripeException {
        ProductCreateParams productParams = ProductCreateParams.builder()
                .setName(invoiceData.getName())
                .build();

        Product product = Product.create(productParams);

        PriceCreateParams priceParams = PriceCreateParams.builder()
                .setProduct(product.getId())
                .setUnitAmount(invoiceData.getAmount().longValue())
                .setCurrency(invoiceData.getCurrency())
                .build();

        Price price = Price.create(priceParams);

        return price;
    }
}
