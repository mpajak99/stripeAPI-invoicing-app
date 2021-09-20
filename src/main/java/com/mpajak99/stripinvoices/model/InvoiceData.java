package com.mpajak99.stripinvoices.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class InvoiceData {

    @NotBlank
    private String customer;
    @NotBlank
    private String name;
    @Positive
    private BigDecimal amount;
    @Positive
    private BigDecimal quantity;
    @NotBlank
    private String currency;
    private String description;
}
