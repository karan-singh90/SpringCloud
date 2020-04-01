package com.application.test.service;


import com.application.test.modalEntity.Employee;
import com.application.test.repository.EmployeeDaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeDaoRepository employeeDao;

    @Override
    public List<Employee> getEmployeeList() {
        return employeeDao.findAll();
    }

    @Override
    public Employee createEmployee(Employee emp) {
        return employeeDao.save(emp);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeDao.deleteById(id);
    }

    @Override
    public void updateEmployee(Employee emp) {
        Optional<Employee> empfromDB = employeeDao.findById(emp.getId());
        Employee empChange = empfromDB.get();
        empChange.setFirst_name(emp.getFirst_name());
        empChange.setLast_name(emp.getLast_name());
        empChange.setAge(emp.getAge());
        empChange.setEmail(emp.getEmail());
        employeeDao.save(empChange);
    }

}
