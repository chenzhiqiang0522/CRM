package org.chenzhiqiang.services.impl;

import org.chenzhiqiang.domain.Department;
import org.chenzhiqiang.mapper.DepartmentMapper;
import org.chenzhiqiang.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public List<Department> getAllDepartments() {
        List<Department> departments = departmentMapper.selectAll();
        return departments;
    }

    @Override
    public Department selectDepartmentByID(Long id) {
        Department department = departmentMapper.selectByID(id);
        return department;
    }

    @Override
    public Integer deleteDepartmentByName(String depname) {
        Integer integer = departmentMapper.deleteByName(depname);
        return integer;
    }

    @Override
    public Integer deleteDepartmentById(Long id) {
        Integer integer = departmentMapper.deleteById(id);
        return integer;
    }

    @Override
    public Long addDepartment(Department department) {
        Long id = departmentMapper.addDepartment(department);
        return id;
    }
}
