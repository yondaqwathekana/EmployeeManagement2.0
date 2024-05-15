package ac.za.cput.service;

import ac.za.cput.domain.Employee;
import ac.za.cput.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository repository;
    @Autowired
    public EmployeeService(EmployeeRepository repository) { this.repository = repository; }

    @Override
    public Employee create(Employee employee) { return repository.save(employee); }
    @Override
    public Employee read(Long employeeNumber) { return repository.findById(employeeNumber).orElseThrow(()-> new NoSuchElementException("Employee Number does not exist")); }
    @Override
    public Employee update(Employee employee) { return repository.save(employee); }
    @Override
    public Employee delete(long employeeNumber) {
        Employee employeeToDelete = read(employeeNumber);
        repository.delete(employeeToDelete);
        return employeeToDelete;
    }
    @Override
    public Set<Employee> getAll() { return new HashSet<>(repository.findAll()); }
}
