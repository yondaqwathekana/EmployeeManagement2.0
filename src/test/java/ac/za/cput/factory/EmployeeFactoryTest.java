package ac.za.cput.factory;

import ac.za.cput.domain.Contact;
import ac.za.cput.domain.Employee;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeFactoryTest {

    @Test
    void buildEmployee() {
        Contact contact = ContactFactory.buildContact("jay@gmail.com", "0795109767", "0784909331");
        Employee employee = EmployeeFactory.buildEmployee(123, "Jay", "Mtolo", contact);

        assertNotNull(contact);
        assertNotNull(employee);
        System.out.println(employee);
    }
}