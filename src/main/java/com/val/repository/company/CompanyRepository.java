package com.val.repository.company;

import org.springframework.data.repository.CrudRepository;

import com.val.model.company.Company;

public interface CompanyRepository extends CrudRepository<Company, Integer> {

}
