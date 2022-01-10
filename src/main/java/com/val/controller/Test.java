package com.val.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.val.model.company.Company;
import com.val.model.employee.Employee;
import com.val.repository.company.CompanyRepository;
import com.val.repository.employee.EmployeeRepository;

@RestController
public class Test {

	@Autowired
	private CompanyRepository cR;
	@Autowired
	private EmployeeRepository eR;

	public void setcR(CompanyRepository cR) {
		this.cR = cR;
	}

	public void seteR(EmployeeRepository eR) {
		this.eR = eR;
	}
	

	@GetMapping(value = "/employee")
	public List<Employee> getEmployeeDataBaseData(){
		List<Employee> list = (List<Employee>) eR.findAll();
		return list;
	}
	
	@GetMapping(value = "/company")
	public List<Company> getCompanyDataBaseData(){
		List<Company> list = (List<Company>) cR.findAll();
		return list;
	}
}
