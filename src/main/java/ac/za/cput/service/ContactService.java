package ac.za.cput.service;

import ac.za.cput.domain.Contact;
import ac.za.cput.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ContactService implements IContactService{

    private final ContactRepository repository;
    @Autowired
    public ContactService(ContactRepository repository) { this.repository = repository; }

    @Override
    public Contact create(Contact contact) { return repository.save(contact); }

    @Override
    public Contact read(String email) { return repository.findById(email).orElseThrow(() -> new NoSuchElementException("Email not found")); }

    @Override
    public Contact update(Contact contact) { return repository.save(contact); }
    @Override
    public Set<Contact> getAll() { return repository.findAll().stream().collect(Collectors.toSet()); }
}