package org.example.repository;

import org.example.model.Department;
import org.example.model.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testSaveEmployee() {
        Department department = new Department();
        department.setName("Engineering");
        department = departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setName("Alice Smith");
        employee.setEmail("alice.smith@example.com");
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);
        Assertions.assertNotNull(savedEmployee.getId());
        Assertions.assertEquals("Alice Smith", savedEmployee.getName());
    }

    @Test
    public void testFindEmployeeByEmail() {
        Department department = new Department();
        department.setName("Human Resources");
        department = departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setName("Bob Johnson");
        employee.setEmail("bob.johnson@example.com");
        employee.setDepartment(department);
        employeeRepository.save(employee);

        Employee foundEmployee = employeeRepository.findByEmail("bob.johnson@example.com");
        Assertions.assertNotNull(foundEmployee);
        Assertions.assertEquals("Bob Johnson", foundEmployee.getName());
    }

    @Test
    public void testFindEmployeesByDepartmentName() {
        Department department = new Department();
        department.setName("Sales");
        department = departmentRepository.save(department);

        Employee employee1 = new Employee();
        employee1.setName("Chris Brown");
        employee1.setEmail("chris.brown@example.com");
        employee1.setDepartment(department);
        employeeRepository.save(employee1);

        Employee employee2 = new Employee();
        employee2.setName("Diana Ross");
        employee2.setEmail("diana.ross@example.com");
        employee2.setDepartment(department);
        employeeRepository.save(employee2);

        List<Employee> employees = employeeRepository.findByDepartmentName("Sales");
        Assertions.assertEquals(2, employees.size());
    }

    @Test
    public void testDeleteEmployee() {
        Department department = new Department();
        department.setName("Logistics");
        department = departmentRepository.save(department);

        Employee employee = new Employee();
        employee.setName("Evan Wright");
        employee.setEmail("evan.wright@example.com");
        employee.setDepartment(department);
        Employee savedEmployee = employeeRepository.save(employee);

        employeeRepository.deleteById(savedEmployee.getId());

        Optional<Employee> deletedEmployee = employeeRepository.findById(savedEmployee.getId());
        Assertions.assertTrue(deletedEmployee.isEmpty());
    }
}
