package org.example;

import org.example.model.Department;
import org.example.model.Employee;
import org.example.repository.DepartmentRepository;
import org.example.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EmployeeDepartmentTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Test
	public void testCreateAndFindDepartment() {
		Department department = new Department();
		department.setName("Finance");
		department = departmentRepository.save(department);

		Department found = departmentRepository.findById(department.getId()).orElse(null);
		Assertions.assertNotNull(found);
		Assertions.assertEquals("Finance", found.getName());
	}

	@Test
	public void testCreateAndFindEmployee() {
		Department department = new Department();
		department.setName("HR");
		department = departmentRepository.save(department);

		Employee employee = new Employee();
		employee.setName("Alice");
		employee.setEmail("alice@example.com");
		employee.setDepartment(department);
		employee = employeeRepository.save(employee);

		Employee found = employeeRepository.findById(employee.getId()).orElse(null);
		Assertions.assertNotNull(found);
		Assertions.assertEquals("Alice", found.getName());
		Assertions.assertEquals("alice@example.com", found.getEmail());
		Assertions.assertEquals("HR", found.getDepartment().getName());
	}

	@Test
	public void testDepartmentEmployeeRelationship() {
		Department department = new Department();
		department.setName("IT");
		department = departmentRepository.save(department);

		Employee employee1 = new Employee();
		employee1.setName("Bob");
		employee1.setEmail("bob@example.com");
		employee1.setDepartment(department);
		employeeRepository.save(employee1);

		Employee employee2 = new Employee();
		employee2.setName("Charlie");
		employee2.setEmail("charlie@example.com");
		employee2.setDepartment(department);
		employeeRepository.save(employee2);

		List<Employee> employees = employeeRepository.findAll();
		Assertions.assertEquals(2, employees.size());
		Assertions.assertEquals("IT", employees.get(0).getDepartment().getName());
		Assertions.assertEquals("IT", employees.get(1).getDepartment().getName());
	}
}
