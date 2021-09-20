package com.mpajak99.stripinvoices.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
public class CustomerData {

    @NotBlank
    private String name;
    @Email
    private String email;
    @NotBlank
    private String country;
    @NotBlank
    private String city;
    @NotBlank
    private String postalCode;
    @NotBlank
    private String street;
    private String description;
}
