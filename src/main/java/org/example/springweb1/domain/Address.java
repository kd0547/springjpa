package org.example.springweb1.domain;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;
}
