package ac.za.cput.controller;

import ac.za.cput.domain.Contact;
import ac.za.cput.factory.ContactFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ContactControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String Base_URL = "http://localhost:8080/manage/contacts";
    private static Contact contact1, contact2;

    @BeforeAll
    public static void setUp() {
        contact1 = ContactFactory.buildContact("jay@gmail.com", "0795109767", "0784909331");
        contact2 = ContactFactory.buildContact("cindy@gmail.com", "0768818080", "0783312787");
    }

    @Test
    @Order(1)
    void createContact() {
        String createURL = Base_URL + "/create";
        ResponseEntity<Contact> postResponse1 = restTemplate.postForEntity(createURL, contact1, Contact.class);
        ResponseEntity<Contact> postResponse2 = restTemplate.postForEntity(createURL, contact2, Contact.class);
        Contact contact1Saved = postResponse1.getBody();
        Contact contact2Saved = postResponse2.getBody();
        assert contact1Saved != null && contact2Saved!= null;
        assertEquals(contact1, contact1Saved);
        assertEquals(contact2, contact2Saved);
        System.out.println("Saved contact1: " + contact1Saved);
        System.out.println("Saved contact2: " + contact2Saved);
    }

    @Test
    @Order(2)
    void readContact() {
        String email = contact1.getEmail();
        String readURL = Base_URL + "/read/" + email;
        System.out.println("URL: " + readURL);
        ResponseEntity<Contact> response = restTemplate.getForEntity(readURL, Contact.class);
        assertNotNull(response);
        System.out.println(response);
        assertEquals(contact1.getEmail(), response.getBody().getEmail());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    @Order(3)
    void updateContact() {
        String updateURL = Base_URL + "/update";
        System.out.println("URL: " + updateURL);
        Contact contactUpdate = new Contact.Builder().copy(contact2).setMobile("0711622622").build();
        HttpEntity<Contact> requestEntity = new HttpEntity<>(contactUpdate);
        ResponseEntity<Contact> response = restTemplate.exchange(updateURL, HttpMethod.PUT, requestEntity, Contact.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Contact contactUpdated = response.getBody();
        assertNotNull(contactUpdated);
        System.out.println(contactUpdated);
    }

    @Test
    @Order(4)
    void getAll() {
        String getAllURL = Base_URL + "/getAll";
        System.out.println("URL: " + getAllURL);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getAllURL, HttpMethod.GET, entity, String.class);
        System.out.println("All employees: " + response);
    }
}