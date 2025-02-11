package com.noman.nbSchool.service;

import com.noman.nbSchool.model.Person;
import com.noman.nbSchool.model.Roles;
import com.noman.nbSchool.repository.PersonRepository;
import com.noman.nbSchool.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;
    private final RolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean createPerson(Person person) {
        boolean isSaved = false;
//        Setting role
        Roles studentRole = rolesRepository.getByRoleName("STUDENT");
        person.setRoles(studentRole);
//        Setting hashPassword
        String hashPwd = passwordEncoder.encode(person.getPwd());
        person.setPwd(hashPwd);
        person = personRepository.save(person);
        if(null!=person && person.getPersonId()>0) isSaved = true;
        return isSaved;
    }
}
