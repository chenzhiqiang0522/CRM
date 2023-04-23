package org.chenzhiqiang.mapper;

import org.chenzhiqiang.domain.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {
    List<Department> selectAll();
    Department selectByID(Long id);
    Integer deleteByName(String depname);
    Integer deleteById(Long id);
    Long addDepartment(Department department);

}
