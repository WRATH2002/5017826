package org.example.repository;

import org.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Find employees by their name
    List<Employee> findByName(String name);

    // Find employees by their department's name
    List<Employee> findByDepartmentName(String departmentName);

    // Find an employee by their email
    Employee findByEmail(String email);
}
