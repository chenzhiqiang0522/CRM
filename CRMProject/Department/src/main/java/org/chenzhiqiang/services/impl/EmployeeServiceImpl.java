package org.chenzhiqiang.services.impl;

import org.chenzhiqiang.domain.Employee;
import org.chenzhiqiang.mapper.EmployeeMapper;
import org.chenzhiqiang.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeMapper.selectAll();
        return employees;
    }
}
