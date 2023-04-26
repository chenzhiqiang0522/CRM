package org.chenzhiqiang.services.impl;

import org.chenzhiqiang.BaseTest;
import org.chenzhiqiang.domain.Employee;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

public class EmployeeServiceImplTest extends BaseTest {
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Test
    public void getAllEmployees() {
        List<Employee> allEmployees = employeeService.getAllEmployees();
        System.out.println(allEmployees);
    }
}