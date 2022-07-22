package com.slf.data.ingestion;

import com.slf.data.ingestion.domain.Person;
import com.slf.data.ingestion.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class PersonRepositoryTest {

    @Autowired
    PersonRepository repository;

    @Test
    @Transactional
    public void someTest() {
        var person = new Person();

        person.setAge(30);
        person.setName("Ivan");
        person.setId(1);

        repository.save(person);

        Assertions.assertEquals(1, repository.findAll().size());
    }
}
