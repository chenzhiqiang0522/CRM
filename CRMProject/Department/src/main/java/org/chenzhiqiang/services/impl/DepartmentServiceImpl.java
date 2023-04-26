package org.chenzhiqiang.services.impl;

import org.chenzhiqiang.domain.Department;
import org.chenzhiqiang.domain.DepartmentQueryObject;
import org.chenzhiqiang.mapper.DepartmentMapper;
import org.chenzhiqiang.services.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
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
    @Transactional
    public Integer deleteDepartmentByName(String depname) {
        Integer integer = departmentMapper.deleteByName(depname);
        return integer;
    }

    @Override
    @Transactional
    public Integer deleteDepartmentById(Long id) {
        Integer integer = departmentMapper.deleteById(id);
        return integer;
    }

    @Override
    @Transactional
    public Long addDepartment(Department department) {
        String path = "";
        Long id = departmentMapper.addDepartment(department);
        if (Objects.isNull(department.getParent())||Objects.isNull(department.getParent().getId()))
            path = "/"+department.getId();
        else{
            Long id1 = department.getParent().getId();
            Department parent = departmentMapper.selectByID(id1);
            path = parent.getPath()+"/"+id1;
        }
        department.setPath(path);
        departmentMapper.modifyDepartment(department);
        return id;
    }

    @Override
    public Integer modifyDepartment(Department department) {
        Integer integer = departmentMapper.modifyDepartment(department);
        return integer;
    }

    @Override
    public List<Department> pageList(DepartmentQueryObject queryObject) {
        List<Department> departments = departmentMapper.seletByQueryObjec(queryObject);
//        System.out.println("pagelist的结果"+departments);
        return departments;
    }

    @Override
    public Integer patchDeleteDepartments(ArrayList<Long> ids) {
        Integer returnInteger = departmentMapper.patchDeleteDepartments(ids);
        return returnInteger;
    }

    @Override
    public List<Department> getChildDepartments() {
        List<Department> departmentsTree = departmentMapper.getChildDepartments();
        return departmentsTree;
    }
}
