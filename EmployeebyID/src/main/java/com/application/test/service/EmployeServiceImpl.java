package com.application.test.service;


import com.application.test.modalEntity.Employee;
import com.application.test.repository.EmployeeDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDaoRepository employeeDao;

    @Override
    public Employee findEmployee(Integer id) {
      Optional<Employee> emp=  employeeDao.findById(id);
        return emp.get();
    }
}
