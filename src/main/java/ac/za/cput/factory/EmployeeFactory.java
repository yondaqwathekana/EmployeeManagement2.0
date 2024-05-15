package ac.za.cput.factory;

import ac.za.cput.domain.Contact;
import ac.za.cput.domain.Employee;
import ac.za.cput.util.Helper;

public class EmployeeFactory {
    public static Employee buildEmployee(long employeeNumber, String firstName, String lastName, Contact contact){
        if(Helper.isNullOrEmpty(employeeNumber) || Helper.isNullOrEmpty(firstName) || Helper.isNullOrEmpty(lastName) || !Helper.isValidEmail(contact.getEmail()) || Helper.isNullOrEmpty(contact.getMobile()) || Helper.isNullOrEmpty(contact.getWorkTelephone()))
            return null;
        return new Employee.Builder().setEmployeeNumber(employeeNumber).setFirstName(firstName).setLastName(lastName).setContact(contact).build();
    }

}
