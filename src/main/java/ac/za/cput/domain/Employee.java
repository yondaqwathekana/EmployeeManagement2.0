package ac.za.cput.domain;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.util.Objects;

@Entity
public class Employee {
    @Id
    private long employeeNumber;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private Contact contact;

    protected Employee(){}

    public Employee(Builder builder) {
        this.employeeNumber = builder.employeeNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.contact = builder.contact;
    }

    public long getEmployeeNumber() { return employeeNumber; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Contact getContact() { return contact; }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Employee employee = (Employee) object;
        return employeeNumber == employee.employeeNumber && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName) && Objects.equals(contact, employee.contact);
    }
    @Override
    public int hashCode() { return Objects.hash(employeeNumber, firstName, lastName, contact); }
    @Override
    public String toString() {
        return "Employee{" +
                "employeeNumber: " + employeeNumber +
                ", firstName: '" + firstName + '\'' +
                ", lastName: '" + lastName + '\'' +
                ", contact:" + contact + '\'' +
                '}';
    }

    public static class Builder{
        private long employeeNumber;
        private String firstName;
        private String lastName;
        private Contact contact;

        public Builder setEmployeeNumber(long employeeNumber) {
            this.employeeNumber = employeeNumber;
            return this;
        }
        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder setContact(Contact contact) {
            this.contact = contact;
            return this;
        }

        public Builder copy(Employee employee){
            this.employeeNumber = employee.employeeNumber;
            this.firstName = employee.firstName;
            this.lastName = employee.lastName;
            this.contact = employee.contact;
            return this;
        }
        public Employee build(){ return new Employee(this); }
    }
}
