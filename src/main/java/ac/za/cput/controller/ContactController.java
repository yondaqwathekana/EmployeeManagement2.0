package ac.za.cput.controller;

import ac.za.cput.domain.Contact;
import ac.za.cput.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;
    @Autowired
    public ContactController(ContactService contactService) { this.contactService = contactService; }
    @PostMapping("/create")
    public Contact createContact(@RequestBody Contact contact){ return contactService.create(contact); }
    @GetMapping("/read/{email}")
    public Contact readContact(@PathVariable String email){ return contactService.read(email); }
    @PutMapping("/update")
    public Contact updateContact(@RequestBody Contact contact){ return contactService.update(contact); }
    @GetMapping("/getAll")
    public Set<Contact> getAll(){ return contactService.getAll(); }
}
