package com.slf.data.ingestion;

import com.slf.data.ingestion.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataIngestionApplication {

    @Autowired
    PersonRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(DataIngestionApplication.class, args);
    }
}
