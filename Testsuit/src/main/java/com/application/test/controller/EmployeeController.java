package com.application.test.controller;

import com.application.test.modalEntity.Employee;
import com.application.test.service.EmployeServiceImpl;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@EnableHystrix
@EnableHystrixDashboard
@RequestMapping("/employee")
@Api(value = "EmployeeInfo", description = "CRUD Opertions for employee")
public class EmployeeController {

    @Autowired
    EmployeServiceImpl employeService;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancer;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getEmployees")
    @ApiOperation(value = "View a list of available employees", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    public List<Employee> getEmployees() {
//        Employee emp1= new Employee(1,"Karan","Singh",30,"ABC");
//        Employee emp2= new Employee(2,"Poonam","Rathore",27,"ABCd");
//             List<Employee> emplist=new ArrayList<>();
//             emplist.add(emp1);
//             emplist.add(emp2);
        List<Employee> emplist = employeService.getEmployeeList();
        return emplist;
    }

    @ApiOperation(value = "Add an empoloyee")
    @RequestMapping(value = "/createEmployee", method = RequestMethod.POST)
    public ResponseEntity<String> createEmployee(@RequestBody Employee emp) {
        Employee empstore = employeService.createEmployee(emp);
        log.info("Data sotred " + empstore);
        return new ResponseEntity<String>("Hello User Your Data is Stored", HttpStatus.CREATED);
    }

    @ApiOperation(value = "Delete an empoloyee")
    @RequestMapping(value = "/deleteEmployee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable Integer id) {
        employeService.deleteEmployee(id);
        log.info("Employee with id " + id + " deleted");
        return "User is deleted";
    }

    @ApiOperation(value = "Update an empoloyee")
    @PutMapping("/updateEmployee")
    public String updateEmployee(@RequestBody Employee emp) {
        employeService.updateEmployee(emp);
        log.info("Employee with id " + emp.getId() + " is updated");
        return "User is Updated";
    }

    @GetMapping("/findEmployee/{id}")
    @ApiOperation(value = "Find an empoloyee by id")
    @HystrixCommand(fallbackMethod = "findbyidfallback")
//  commandProperties = { @HystrixProperty(name="execution.isolation.thread.timeoutInMilliSeconds", value="6000")})
    public Employee findById(@PathVariable Integer id) {
        // Employee response= restTemplate.getForObject("http://localhost:8090/employesservice/findEmployeebyID/{id}",Employee.class,id);
        List<ServiceInstance> instances = discoveryClient.getInstances("ZullServer");
        ServiceInstance serviceInstance = instances.get(0);
//        ServiceInstance serviceInstance=loadBalancer.choose("employeebyID"); //By ribon client

        log.info("Service Instance port:::" + serviceInstance.getPort());
        String baseUrl = serviceInstance.getUri().toString();
        baseUrl = baseUrl + "/employeController/findEmployeebyID/{id}";
        log.info("Service Instance url:::" + baseUrl);
        Employee response = null;
        try {
            response = restTemplate.getForObject(baseUrl, Employee.class, id);
            log.info("response:::" + response);
        } catch (HttpClientErrorException e) {
            log.info("Error Message ::" + e.getMessage());
        } catch (HttpServerErrorException e) {
            log.info("Error Message ::" + e.getMessage());
        }
//         log.info("Getting response from other service");
        return response;
    }

    public Employee findbyidfallback(@PathVariable Integer id) {
        return new Employee(0, "Dummy", "Dummy", 0, "Dummy");
    }
}
