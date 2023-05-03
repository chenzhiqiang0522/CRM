package org.chenzhiqiang.services;

import org.chenzhiqiang.domain.Employee;
import org.chenzhiqiang.utils.QueryObj;

import java.util.ArrayList;
import java.util.List;

public interface IEmployeeService {
    List<Employee> getAllEmployees();
    List<Employee> selectByQueryObj(QueryObj queryObj);
    Long addEmployee(Employee employee);
    Integer deleteById(Long id);
    Integer batchDelete(ArrayList<Long> ids);
    Integer modifyEmployee(Employee employee);
}
