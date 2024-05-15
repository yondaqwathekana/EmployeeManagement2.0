package ac.za.cput.service;

import ac.za.cput.domain.Contact;
import ac.za.cput.factory.ContactFactory;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    private static final Contact contact1 = ContactFactory.buildContact("jay@gmail.com", "0795109767", "0784909331");
    private static final Contact contact2 = ContactFactory.buildContact("cindy@gmail.com", "0768818080", "0783312787");

    @Test
    @Order(1)
    void create() {
        Contact createdContact1 = contactService.create(contact1);
        Contact createdContact2 = contactService.create(contact2);

        assertNotNull(createdContact1);
        assertNotNull(createdContact2);
        System.out.println(createdContact1);
        System.out.println(createdContact2);
    }

    @Test
    @Order(2)
    void read() {
        Contact findContact = contactService.read("cindy@gmail.com");
        assertNotNull(findContact);
        System.out.println(findContact);
    }

    @Test
    @Order(3)
    void update() {
        Contact createContact2 = contactService.update(contact2);
        assertNotNull(createContact2);
        if(contact2 != null){
            Contact updateContact2 = contactService.update(new Contact.Builder().copy(contact2).setMobile("0711622622").build());
            assertNotNull(updateContact2);
            System.out.println(updateContact2);
        }
    }

    @Test
    @Order(4)
    void getAll() {
        Set<Contact> contactSet = contactService.getAll();
        assertNotNull(contactSet);
        System.out.println(contactSet);
    }
}