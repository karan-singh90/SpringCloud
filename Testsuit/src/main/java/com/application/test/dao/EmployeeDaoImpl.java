//package com.application.test.Dao;
//
//import com.application.test.modals.Employee;
//import org.springframework.data.cassandra.repository.CassandraRepository;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Slice;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface EmployeeDaoImpl implements  CassandraRepository<Employee,Integer> {
//
//    public List<Employee> getEmployee() {
//        return findAll();
//    }
//
////    @Override
////    public <S extends Employee> S save(S s) {
////        return null;
////    }
////
////    @Override
////    public <S extends Employee> List<S> saveAll(Iterable<S> iterable) {
////        return null;
////    }
////
////    @Override
////    public Optional<Employee> findById(Integer integer) {
////        return Optional.empty();
////    }
////
////    @Override
////    public boolean existsById(Integer integer) {
////        return false;
////    }
////
////    @Override
////    public List<Employee> findAll() {
////        return null;
////    }
////
////    @Override
////    public List<Employee> findAllById(Iterable<Integer> iterable) {
////        return null;
////    }
////
////    @Override
////    public long count() {
////        return 0;
////    }
////
////    @Override
////    public void deleteById(Integer integer) {
////
////    }
////
////    @Override
////    public void delete(Employee employee) {
////
////    }
////
////    @Override
////    public void deleteAll(Iterable<? extends Employee> iterable) {
////
////    }
////
////    @Override
////    public void deleteAll() {
////
////    }
////
////    @Override
////    public Slice<Employee> findAll(Pageable pageable) {
////        return null;
////    }
////
////    @Override
////    public <S extends Employee> S insert(S s) {
////        return null;
////    }
////
////    @Override
////    public <S extends Employee> List<S> insert(Iterable<S> iterable) {
////        return null;
////    }
//}
