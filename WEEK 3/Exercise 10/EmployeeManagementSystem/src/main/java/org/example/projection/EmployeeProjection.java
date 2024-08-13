package org.example.repository;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {

	Long getId();

	@Value("#{target.name}")
	String getName();

	@Value("#{target.email}")
	String getEmail();

	@Value("#{target.department.name}")
	String getDepartmentName();
}
