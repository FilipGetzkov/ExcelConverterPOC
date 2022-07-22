package com.slf.data.ingestion.domain;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Person")
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int age;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Pet> pets = new ArrayList<>();

    public void addAddress(Address address) {
        addresses.add(address);
        address.setPerson(this);
    }

    public void addPet(Pet pet) {
        pets.add(pet);
        pet.setPerson(this);
    }

    @Override
    public String toString() {
        return "ID: " + this.id + ", " + " NAME: " + this.name + ", " + " AGE: " + this.age + ", " + " ADDRESS:  " + this.getAddresses().toString() + ", " + " PET: " + this.getPets().toString();
    }
}
