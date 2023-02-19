package com.wen.rfspringboot.repository;

import com.wen.rfspringboot.pojo.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer> {
    List<Person> findPersonByAddress_city(String cityName);
}