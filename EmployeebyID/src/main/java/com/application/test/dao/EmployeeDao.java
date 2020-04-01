package com.application.test.dao;

import com.application.test.modalEntity.Employee;

import java.util.List;

public interface EmployeeDao {
    List<Employee> getEmployee();
    Employee createEmployee(Employee emp);

}
