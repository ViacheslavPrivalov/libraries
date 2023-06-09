package com.example.libraries.service;

import com.example.libraries.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface EmployeeBookService {

    Employee addEmployee(String firstName, String lastName, Integer departmentId, Double salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> findAll();

    Employee maxSalary(Integer departmentId);

    Employee minSalary(Integer departmentId);

    Collection<Employee> printEmployeesByDepartment(Integer departmentId);

    Map<Integer, List<Employee>> printEmployees();

}
