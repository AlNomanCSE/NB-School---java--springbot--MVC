package com.noman.nbSchool.service;

import com.noman.nbSchool.model.Person;
import com.noman.nbSchool.model.Roles;
import com.noman.nbSchool.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createPerson(Person person) {
        boolean isSaved = false;
//        Setting hashPassword
        Person to_be_saved_person= new Person();
        to_be_saved_person.setName(person.getName());
        to_be_saved_person.setEmail(person.getEmail());
        to_be_saved_person.setMobileNumber(person.getMobileNumber());

        to_be_saved_person.setRoles(Roles.STUDENT);

        String hashPwd = passwordEncoder.encode(person.getPwd());
        to_be_saved_person.setPwd(hashPwd);
        to_be_saved_person.setCreateBy(person.getEmail());
        Person savedPerson = personRepository.save(to_be_saved_person);
        if(null!=savedPerson) isSaved = true;
        return isSaved;
    }
}
