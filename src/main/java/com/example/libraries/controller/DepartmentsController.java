package com.example.libraries.controller;

import com.example.libraries.model.Employee;
import com.example.libraries.service.EmployeeBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    private final EmployeeBookService employeeBookService;


    public DepartmentsController(EmployeeBookService employeeBookService) {
        this.employeeBookService = employeeBookService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary(@RequestParam Integer departmentId) {
        return employeeBookService.maxSalary(departmentId);
    }
    @GetMapping("/min-salary")
    public Employee minSalary(@RequestParam Integer departmentId) {
        return employeeBookService.minSalary(departmentId);
    }

    @GetMapping(value = "/all", params = "departmentId")
    public Collection<Employee> printEmployeesByDepartment(@RequestParam Integer departmentId) {
        return employeeBookService.printEmployeesByDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> printEmployees() {
        return employeeBookService.printEmployees();
    }

}

