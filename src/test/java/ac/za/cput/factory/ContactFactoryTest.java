package ac.za.cput.factory;

import ac.za.cput.domain.Contact;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactFactoryTest {

    @Test
    @Order(1)
    void buildContactWithValidContactDetails() {
        Contact contact = ContactFactory.buildContact("jay@gmail.com", "0795109767", "0784909331");
        assertNotNull(contact);
        System.out.println(contact);
    }

    @Test
    @Order(2)
    void buildContactWithInvalidContactEmail(){
        Contact contact = ContactFactory.buildContact("123@123.123", "0795109767", "0784909331");
        assertNotNull(contact);
        System.out.println(contact);
    }

    @Test
    @Order(3)
    void buildContactWithInvalidMobileContact(){
        Contact contact = ContactFactory.buildContact("123@123.com", "abcdefghi", "0784909331");
        assertNotNull(contact);
        System.out.println(contact);
    }
}