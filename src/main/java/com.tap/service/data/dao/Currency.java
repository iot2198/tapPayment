package com.tap.service.data.dao;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "currency")
public class Currency implements Serializable {

    private static final long serialVersionUID = 123765351514001L;

    @Id
    @Column(name = "name")
    String currency;

    @Column(name = "code")
    String code;

    @Column(name = "country")
    String country;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
