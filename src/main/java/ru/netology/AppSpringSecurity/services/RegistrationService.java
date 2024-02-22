package ru.netology.AppSpringSecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.netology.AppSpringSecurity.models.Person;
import ru.netology.AppSpringSecurity.repositories.PeopleRepository;

@Service
public class RegistrationService {

    private final PeopleRepository peopleRepository;
    private final PasswordEncoder getPasswordEncoder;

    @Autowired
    public RegistrationService(PeopleRepository peopleRepository, PasswordEncoder getPasswordEncoder) {
        this.peopleRepository = peopleRepository;
        this.getPasswordEncoder = getPasswordEncoder;
    }

    @Transactional
    public void register(Person person) {
        person.setPassword(getPasswordEncoder.encode(person.getPassword()));
        person.setRole("ROLE_USER");
        peopleRepository.save(person);
    }

}
