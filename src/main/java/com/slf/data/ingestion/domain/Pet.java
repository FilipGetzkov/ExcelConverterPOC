package com.slf.data.ingestion.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Pet")
@Data
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String species;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public String toString() {
        return "ID: " + this.id + ", " + " name: " + this.name + ", " + " age: " + this.age + ", " + " species:  " + this.species;
    }

}
