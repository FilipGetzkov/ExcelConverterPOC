package com.slf.data.ingestion.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String country;
    private String city;
    private String street;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public String toString() {
        return "ID: " + this.id + ", " + " country: " + this.country + ", " + " city: " + this.city + ", " + " street:  " + this.street;
    }

}
