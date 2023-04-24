package org.chenzhiqiang.services;

import org.chenzhiqiang.domain.Department;
import org.chenzhiqiang.domain.DepartmentQueryObject;

import java.util.List;

public interface IDepartmentService {
    List<Department> getAllDepartments();
    Department selectDepartmentByID(Long id);
    Integer deleteDepartmentByName(String depname);
    Integer deleteDepartmentById(Long id);
    Long addDepartment(Department department);
    Integer modifyDepartment(Department department);
    List<Department> pageList(DepartmentQueryObject queryObject);
}
