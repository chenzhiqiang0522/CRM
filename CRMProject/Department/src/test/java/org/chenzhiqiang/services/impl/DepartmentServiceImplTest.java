package org.chenzhiqiang.services.impl;

import org.chenzhiqiang.BaseTest;
import org.chenzhiqiang.domain.Department;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentServiceImplTest extends BaseTest {
    @Autowired
    private DepartmentServiceImpl departmentService;

    @Test
    public void getAllDepartment() {
        List<Department> allDepartments = departmentService.getAllDepartments();
        allDepartments.forEach(System.out::println);
    }

    @Test
    public void selectDepartmentByID() {
        Department department = departmentService.selectDepartmentByID(45L);
        System.out.println(department);
    }

    @Test
    public void deleteDepartmentByName() {
        Integer integer = departmentService.deleteDepartmentByName("utils");
    }

    @Test
    public void addDepartment() {
        Department department = new Department("utils", "this s a   jj k k  l  k uj i ");
        departmentService.addDepartment(department);
        System.out.println("返回的id为："+department.getId());
    }

    @Test
    public void deleteDepartmentById() {
        Integer integer = departmentService.deleteDepartmentById(52L);
    }
}