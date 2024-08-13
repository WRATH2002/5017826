package org.example.repository;

import org.example.model.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class DepartmentRepositoryTests {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setName("Marketing");

        Department savedDepartment = departmentRepository.save(department);
        Assertions.assertNotNull(savedDepartment.getId());
        Assertions.assertEquals("Marketing", savedDepartment.getName());
    }

    @Test
    public void testFindDepartmentByName() {
        Department department = new Department();
        department.setName("Research and Development");
        departmentRepository.save(department);

        Department foundDepartment = departmentRepository.findByName("Research and Development");
        Assertions.assertNotNull(foundDepartment);
        Assertions.assertEquals("Research and Development", foundDepartment.getName());
    }

    @Test
    public void testDeleteDepartment() {
        Department department = new Department();
        department.setName("Customer Support");
        Department savedDepartment = departmentRepository.save(department);

        departmentRepository.deleteById(savedDepartment.getId());

        Optional<Department> deletedDepartment = departmentRepository.findById(savedDepartment.getId());
        Assertions.assertTrue(deletedDepartment.isEmpty());
    }
}
