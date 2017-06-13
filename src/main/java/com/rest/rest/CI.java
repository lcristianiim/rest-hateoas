package com.rest.rest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CI {
    @Id
    @GeneratedValue
    private Long id;
    private String CNP;
    private String serie;
    private String number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumber() {
        return number;
    }

    public CI(String CNP, String serie, String number) {
        this.CNP = CNP;
        this.serie = serie;
        this.number = number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public CI() {

    }
}
