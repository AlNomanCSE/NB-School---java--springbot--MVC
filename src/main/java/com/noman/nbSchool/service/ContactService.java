package com.noman.nbSchool.service;

import com.noman.nbSchool.model.Contact;
import com.noman.nbSchool.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class ContactService {
    private final ContactRepository contactRepository;

    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        Contact saveContact = contactRepository.save(contact);
        if (null != saveContact && saveContact.getContactId() > 0) {
            return isSaved;
        }
        return isSaved;
    }
    public List<Contact> findAllMessages() {
        Iterable<Contact> all = contactRepository.findAll();
        List<Contact> messages = new ArrayList<>();
        for (Contact contact : all) {
            messages.add(contact);
        }
        return messages;
    }


}
