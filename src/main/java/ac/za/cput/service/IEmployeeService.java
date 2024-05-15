package ac.za.cput.service;

import ac.za.cput.domain.Employee;

import java.util.Set;

public interface IEmployeeService extends IService<Employee, Long>{

    public Employee delete(long employeeNumber);
    public Set<Employee> getAll();
}
