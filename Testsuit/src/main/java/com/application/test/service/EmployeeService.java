package com.application.test.service;

import com.application.test.modalEntity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployeeList();

    Employee createEmployee(Employee emp);

    void deleteEmployee(Integer id);

    void updateEmployee(Employee emp);

}
