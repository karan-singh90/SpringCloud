package com.application.test.controller;

import com.application.test.modalEntity.Employee;
import com.application.test.service.EmployeServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.RestTemplate;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/employeController")
@Api(value="employeeservice")
public class EmployeeController {

    @Autowired
    EmployeServiceImpl employeService;

    @GetMapping("/findEmployeebyID/{id}")
    @ApiOperation(value = "Find an empoloyee by id")
    public Employee findById(@PathVariable Integer id){
        log.info("Inside method findEmployeeByID");
      Employee emp= employeService.findEmployee(id);
      log.info("Employee found with id"+id);
       return emp;
    }

}
