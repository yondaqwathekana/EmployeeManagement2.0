package ac.za.cput.controller;

import ac.za.cput.domain.Contact;
import ac.za.cput.domain.Employee;
import ac.za.cput.factory.ContactFactory;
import ac.za.cput.factory.EmployeeFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String Base_URL = "http://localhost:8080/manage/employees";
    private static Contact contact1, contact2;
    private static Employee employee1, employee2;

    @BeforeAll
    public static void setUp() {
        contact1 = ContactFactory.buildContact("jay@gmail.com", "0795109767", "0784909331");
        employee1 = EmployeeFactory.buildEmployee(123, "Jay", "Mtolo", contact1);
        contact2 = ContactFactory.buildContact("cindy@gmail.com", "0795109767", "0784909331");
        employee2 = EmployeeFactory.buildEmployee(123, "Cindy", "Hlophe", contact2);
    }

    @Test
    @Order(1)
    void createEmployee() {
        String createURL = Base_URL + "/create";
        ResponseEntity<Employee> response1 = restTemplate.postForEntity(createURL, employee1, Employee.class);
        ResponseEntity<Employee> response2 = restTemplate.postForEntity(createURL, employee2, Employee.class);
        Employee employee1Saved = response1.getBody();
        Employee employee2Saved = response2.getBody();
        assert employee1Saved != null && employee2Saved != null;
        System.out.println("Employee1 Saved: " + employee1Saved);
        System.out.println("Employee2 Saved: " + employee2Saved);
    }

    @Test
    @Order(2)
    void readEmployee() {
    }

    @Test
    @Order(3)
    void updateEmployee() {
    }

    @Test
    @Order(5)
    void deleteEmployee() {
    }

    @Test
    @Order(6)
    void getAllEmployees() {
    }
}