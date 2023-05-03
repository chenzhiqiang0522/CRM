package org.chenzhiqiang.mapper;

import org.chenzhiqiang.domain.Employee;
import org.chenzhiqiang.utils.QueryObj;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EmployeeMapper {
    List<Employee> selectAll();
    Integer deleteById(Long id);
    Integer batchDelete(ArrayList<Long> ids);
    Integer modifyEmployee(Employee employee);
    Long addEmployee(Employee employee);
    List<Employee> selectByQueryObj(QueryObj queryObj);
}
