package com.application.test.repository;

import com.application.test.modalEntity.Employee;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDaoRepository extends CassandraRepository<Employee,Integer> {

}
