package com.noman.nbSchool.service;

import com.noman.nbSchool.model.Person;
import com.noman.nbSchool.model.Roles;
import com.noman.nbSchool.repository.PersonRepository;
import com.noman.nbSchool.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final RolesRepository rolesRepository;

    public boolean createPerson(Person person) {
        boolean isSaved = false;
        Roles studentRole = rolesRepository.getByRoleName("STUDENT");
        person.setRoles(studentRole);
        person = personRepository.save(person);
        if(null!=person && person.getPersonId()>0) isSaved = true;
        return isSaved;
    }
}
