package org.chenzhiqiang.mapper;

import org.chenzhiqiang.BaseTest;
import org.chenzhiqiang.domain.Department;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DepartmentMapperTest extends BaseTest {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void selectAll() {
        List<Department> departments = departmentMapper.selectAll();
        System.out.println(departments);
    }

    @Test
    public void selectByID() {
        Department department = departmentMapper.selectByID(48L);
        System.out.println(department);
    }

    @Test
    public void addDepartment() {
        Department department = new Department("utils", "this s a   jj k k  l  k uj i ");
        departmentMapper.addDepartment(department);
        System.out.println("返回的id为："+department.getId());
    }

    @Test
    public void deleteByName() {
        String name = "utils";
        Integer integer = departmentMapper.deleteByName(name);
        System.out.println("执行结果为"+integer);
    }

    @Test
    public void deleteById() {
        Integer integer = departmentMapper.deleteById(50L);
        System.out.println("执行结果为"+integer);
    }
}