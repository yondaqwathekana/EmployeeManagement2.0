package ac.za.cput.service;

import ac.za.cput.domain.Contact;
import ac.za.cput.domain.Employee;
import ac.za.cput.factory.ContactFactory;
import ac.za.cput.factory.EmployeeFactory;
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
class EmployeeServiceTest {
    @Autowired
    private ContactService contactService;
    @Autowired
    private EmployeeService employeeService;
    // employee 1
    private static final Contact contact1 = ContactFactory.buildContact("jay@gmail.com", "0795109767", "0784909331");
    private static final Employee employee1 = EmployeeFactory.buildEmployee(123, "Jay", "Mtolo", contact1);
    // employee 2
    private static final Contact contact2 = ContactFactory.buildContact("cindy@gmail.com", "0768818080", "0783312787");
    private static final Employee employee2 = EmployeeFactory.buildEmployee(456, "Cindy", "Hlophe", contact2);

    private static final Contact contact3 = ContactFactory.buildContact("luyanda@gmail.com", "0619876778", "0897654567");
    private static final Employee employee3 = EmployeeFactory.buildEmployee(789, "Yonda", "Qwathekana", contact3);


    @Test
    @Order(1)
    void create() {
        Contact createContact1 = contactService.create(contact1);
        Employee createEmployee1 = employeeService.create(employee1);
        Contact createContact2 = contactService.create(contact2);
        Employee createEmployee2 = employeeService.create(employee2);
        Contact createContact3 = contactService.create(contact3);
        Employee createEmployee3 = employeeService.create(employee3);
        assertNotNull(createContact1);
        assertNotNull(createEmployee1);
        assertNotNull(createContact2);
        assertNotNull(createEmployee2);
        assertNotNull(createContact3);
        assertNotNull(createEmployee3);
        System.out.println(createEmployee1);
        System.out.println(createEmployee2);
        System.out.println(createEmployee3);
    }

    @Test
    @Order(2)
    void read() {
        Employee findEmployee1 = employeeService.read(123L);
        assertNotNull(findEmployee1);
        System.out.println(findEmployee1);
    }

    @Test
    @Order(3)
    void update() {
        Contact createContact1 = contactService.create(contact1);
        Employee createEmployee1 = employeeService.create(employee1);
        assertNotNull(createContact1);
        assertNotNull(createEmployee1);

        if (employee1 != null) {
            Employee updateEmployee1 = employeeService.update(new Employee.Builder().copy(employee1).setLastName("Kwababa").build());
            assertNotNull(updateEmployee1);
            System.out.println(updateEmployee1);
        }
    }

    @Test
    @Order(5)
    void delete() {
        Employee findEmployee3 = employeeService.read(789L);
        if(findEmployee3 != null){ employeeService.delete(findEmployee3.getEmployeeNumber()); }
    }

    @Test
    @Order(4)
    void getAll() {
        Set<Employee> allEmployees = employeeService.getAll();
        assertNotNull(allEmployees);
        System.out.println(allEmployees);
    }
}