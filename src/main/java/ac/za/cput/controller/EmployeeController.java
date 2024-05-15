package ac.za.cput.controller;

import ac.za.cput.domain.Employee;
import ac.za.cput.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @PostMapping("/create")
    public Employee createEmployee(@RequestBody Employee employee){ return employeeService.create(employee); }
    @GetMapping("/read/{employeeNumber}")
    public Employee readEmployee(@PathVariable long employeeNumber){ return employeeService.read(employeeNumber); }
    @PutMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee){ return employeeService.update(employee); }
    @DeleteMapping("/delete/{employeeNumber}")
    public Employee deleteEmployee(@PathVariable long employeeNumber){ return employeeService.delete(employeeNumber); }
    @GetMapping("/getAll")
    public Set<Employee> getAllEmployees(){ return employeeService.getAll(); }

}
