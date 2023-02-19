package com.wen.rfspringboot;

import com.wen.pojo.SimpleBean;
import com.wen.rfspringboot.mapper.AccountMapper;
import com.wen.rfspringboot.pojo.Account;
import com.wen.rfspringboot.pojo.Address;
import com.wen.rfspringboot.pojo.Person;
import com.wen.rfspringboot.pojo.Resume;
import com.wen.rfspringboot.repository.PersonRepository;
import com.wen.rfspringboot.repository.ResumeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RfSpringBootApplicationTests {

    @Autowired
    private SimpleBean simpleBean;

    @Test
    void contextLoads() {
        System.out.println(simpleBean);
    }

    @Autowired
    private AccountMapper accountMapper;

    @Test
    void mybatisTest() {
        System.out.println(accountMapper.findByName("wen"));
    }

    @Test
    void mybatisXMLTest() {
        List<Account> accounts = accountMapper.findAll();
        System.out.println(accounts);
    }


    @Autowired
    private ResumeRepository resumeRepository;

    @Test
    public void testSpringBootJpa() {
        List<Resume> resumes = resumeRepository.findAll();
        System.out.println(resumes);
    }

    @Autowired
    private PersonRepository personRepository;

    @Test
    void testSpringBootRedis() {
        Person person = new Person();
        person.setFirstname("wen");
        person.setLastname("lan");
        Address address = new Address();
        address.setCity("北京");
        address.setCountry("china");
        person.setAddress(address);
        personRepository.save(person);
    }


    @Test
    void testSpringBootQuery() {
        List<Person> persons = personRepository.findPersonByAddress_city("北京");
        for (Person person : persons) {
            System.out.println(person);
        }
    }
}
