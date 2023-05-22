package com.example.libraries.service;

import com.example.libraries.exceptions.EmployeeAlreadyAddedException;
import com.example.libraries.exceptions.EmployeeNotFoundException;
import com.example.libraries.exceptions.InvalidInputException;
import com.example.libraries.model.Employee;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeBookServiceImpl implements EmployeeBookService {

    private Map<String, Employee> mapEmployeeBook = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, Integer departmentId, Double salary) {
        validateInput(firstName, lastName);

        String key = firstName + " " + lastName;
        if (mapEmployeeBook.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, lastName, departmentId, salary);
        mapEmployeeBook.put(key, new Employee(firstName, lastName, departmentId, salary));
        return employee;
    }


    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);

        String key = firstName + " " + lastName;
        if (mapEmployeeBook.containsKey(key)) {
            return mapEmployeeBook.remove(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        validateInput(firstName, lastName);

        String key = firstName + " " + lastName;
        if (mapEmployeeBook.containsKey(key)) {
            return mapEmployeeBook.get(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(mapEmployeeBook.values());
    }

    @Override
    public Employee maxSalary(Integer departmentId) {
        return mapEmployeeBook.values().stream()
                .filter(value -> Objects.equals(value.getDepartmentId(), departmentId))
                .max(Comparator.comparingDouble(employee -> employee.getSalary()))
                .get();
    }

    @Override
    public Employee minSalary(Integer departmentId) {
        List<Employee> employeeList = mapEmployeeBook.values().stream()
                .filter(value -> Objects.equals(value.getDepartmentId(), departmentId))
                .collect(Collectors.toList());
        return employeeList.stream()
                .min(Comparator.comparingDouble(employee -> employee.getSalary()))
                .get();
    }

    @Override
    public Collection<Employee> printEmployeesByDepartment(Integer departmentId) {
        List<Employee> employeeList = mapEmployeeBook.values().stream()
                .filter(value -> Objects.equals(value.getDepartmentId(), departmentId))
                .collect(Collectors.toList());
        return Collections.unmodifiableList(employeeList);
    }

    @Override
    public Map<Integer, List<Employee>> printEmployees() {
//        Map<Integer, List<Employee>> employeeMap = new HashMap<>();
//        Set<Integer> departmentsSet = new HashSet<>();
//        for (Employee value : mapEmployeeBook.values()) {
//            departmentsSet.add(value.getDepartmentId());
//        }
//        List<Integer> departmentsList = new ArrayList<>();
//        for (Integer integer : departmentsSet) {
//            departmentsList.add(integer);
//        }
//        for (Employee value : mapEmployeeBook.values()) {
//            for (int i = 0; i < departmentsList.size(); i++) {
//                if (value.getDepartmentId().equals(departmentsList.get(i))) {
//                    List<Employee> list = new ArrayList<>();
//                    list.add(value);
//                    employeeMap.put(departmentsList.get(i), list);
//                }
//            }
//        }
//        return employeeMap;
        return mapEmployeeBook.values().stream()
                .collect(groupingBy(employee -> employee.getDepartmentId()));
    }

    private void validateInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new InvalidInputException();
        }
    }
}
