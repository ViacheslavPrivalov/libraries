package com.example.libraries.controller;

import com.example.libraries.model.Employee;
import com.example.libraries.service.EmployeeBookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeBookController {
    private final EmployeeBookService employeeBookService;

    public EmployeeBookController(EmployeeBookService employeeBookService) {
        this.employeeBookService = employeeBookService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam Integer departmentId,
                                @RequestParam Double salary) {
        return employeeBookService.addEmployee(firstName, lastName, departmentId, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeBookService.removeEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) {
        return employeeBookService.findEmployee(firstName, lastName);
    }

    @GetMapping("/findAll")
    public Collection<Employee> findAll() {
        return employeeBookService.findAll();
    }

}
