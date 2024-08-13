package org.example.repository;

import org.example.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Pagination and sorting with method name
    Page<Employee> findByNameContaining(String name, Pageable pageable);

    // Custom method for pagination and sorting
    Page<Employee> findAll(Pageable pageable);
}
