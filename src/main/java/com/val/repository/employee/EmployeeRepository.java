package com.val.repository.employee;

import org.springframework.data.repository.CrudRepository;

import com.val.model.employee.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer>{

}
