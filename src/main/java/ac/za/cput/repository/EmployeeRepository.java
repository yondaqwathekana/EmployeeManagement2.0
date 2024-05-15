package ac.za.cput.repository;

import ac.za.cput.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    public Employee findEmployeeByFirstName(String firstName);
    public Employee searchEmployeeByEmployeeNumber(long employeeNumber);
    public Employee findEmployeeByContact_EmailOrContact_Mobile(String email, String mobile);
}
