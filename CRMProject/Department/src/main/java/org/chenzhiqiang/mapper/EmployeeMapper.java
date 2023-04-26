package org.chenzhiqiang.mapper;

import org.chenzhiqiang.domain.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeMapper {
    List<Employee> selectAll();
}
