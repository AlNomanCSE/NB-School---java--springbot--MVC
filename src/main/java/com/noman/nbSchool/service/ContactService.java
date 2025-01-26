package com.noman.nbSchool.service;

import com.noman.nbSchool.model.Contact;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ContactService {

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        log.info(contact.toString());
        return isSaved;
    }
}
