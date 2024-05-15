package ac.za.cput.controller;

import ac.za.cput.domain.Contact;
import ac.za.cput.domain.Employee;
import ac.za.cput.factory.ContactFactory;
import ac.za.cput.factory.EmployeeFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private final String Base_URL = "http://localhost:8080/manage/employees";
    private static Contact contact1, contact2, contact3;
    private static Employee employee1, employee2, employee3;

    @BeforeAll
    public static void setUp() {
        contact1 = ContactFactory.buildContact("jay@gmail.com", "0795109767", "0784909331");
        employee1 = EmployeeFactory.buildEmployee(123, "Jay", "Mtolo", contact1);
        contact2 = ContactFactory.buildContact("cindy@gmail.com", "0765678432", "0784851014");
        employee2 = EmployeeFactory.buildEmployee(456, "Cindy", "Hlophe", contact2);
        contact3 = ContactFactory.buildContact("yonda@gmail.com", "0619876534", "0768818989");
        employee3 = EmployeeFactory.buildEmployee(789, "Yonda", "Qwathekana", contact3);
    }

    @Test
    @Order(1)
    void createEmployee() {
        String createURL = Base_URL + "/create";
        ResponseEntity<Employee> response1 = restTemplate.postForEntity(createURL, employee1, Employee.class);
        ResponseEntity<Employee> response2 = restTemplate.postForEntity(createURL, employee2, Employee.class);
        ResponseEntity<Employee> response3 = restTemplate.postForEntity(createURL, employee3, Employee.class);
        Employee employee1Saved = response1.getBody();
        Employee employee2Saved = response2.getBody();
        Employee employee3Saved = response3.getBody();
        assert employee1Saved != null && employee2Saved != null && employee3Saved != null;
        System.out.println("Employee1 Saved: " + employee1Saved);
        System.out.println("Employee2 Saved: " + employee2Saved);
        System.out.println("Employee3 Saved: " + employee3Saved);
    }

    @Test
    @Order(2)
    void readEmployee() {
        String employeeNumber = String.valueOf(employee2.getEmployeeNumber());
        String readURL = Base_URL + "/read/" + employeeNumber;
        System.out.println("URL: " + readURL);
        ResponseEntity<Employee> response1 = restTemplate.getForEntity(readURL, Employee.class);
        assertNotNull(response1);
        System.out.println(response1);
        assertEquals(employee2.getEmployeeNumber(), response1.getBody().getEmployeeNumber());
        System.out.println("Read: " + response1.getBody());
    }

    @Test
    @Order(3)
    void updateEmployee() {
        String updateURL = Base_URL + "/update";
        System.out.println("URL: " + updateURL);
        Contact contactUpdate = new Contact.Builder().copy(contact2).setMobile("0711622622").build();
        Employee employeeUpdate = new Employee.Builder().copy(employee2).setFirstName("Sindiswa").setContact(contactUpdate).build();
        HttpEntity<Employee> requestEntity = new HttpEntity<>(employeeUpdate);
        ResponseEntity<Employee> response = restTemplate.exchange(updateURL, HttpMethod.PUT, requestEntity, Employee.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Employee updatedEmployee = response.getBody();
        assertNotNull(updatedEmployee);
        System.out.println(updatedEmployee);
    }

    @Test
    @Order(5)
    void deleteEmployee() {
        String employeeNumber = String.valueOf(employee3.getEmployeeNumber());
        String deleteURL = Base_URL + "/delete/" + employeeNumber;
        System.out.println("URL: " + deleteURL);
        restTemplate.delete(deleteURL);
        System.out.println("Deleted employee");
    }

    @Test
    @Order(4)
    void getAllEmployees() {
        String getAllURL = Base_URL + "/getAll";
        System.out.println("URL: " + getAllURL);
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getAllURL, HttpMethod.GET, entity, String.class);
        System.out.println("All employees: " + response);
    }
}