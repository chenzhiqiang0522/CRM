package org.chenzhiqiang.services.impl;

import org.chenzhiqiang.domain.Employee;
import org.chenzhiqiang.dto.EmployeeRoleDTO;
import org.chenzhiqiang.mapper.EmployeeMapper;
import org.chenzhiqiang.services.IEmployeeService;
import org.chenzhiqiang.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class EmployeeServiceImpl implements IEmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeMapper.selectAll();
        return employees;
    }

    @Override
    public List<Employee> selectByQueryObj(QueryObj queryObj) {
        List<Employee> employees = employeeMapper.selectByQueryObj(queryObj);
        return employees;
    }

    @Transactional
    @Override
    public Long addEmployee(Employee employee) {
        Long inserId = employeeMapper.addEmployee(employee);
        return inserId;
    }
    @Transactional
    @Override
    public Integer deleteById(Long id) {
        Integer delRowNum = employeeMapper.deleteById(id);
        return delRowNum;
    }

    @Transactional
    @Override
    public Integer batchDelete(ArrayList<Long> ids) {
        Integer delRowsNum = employeeMapper.batchDelete(ids);
        return delRowsNum;
    }

    @Transactional
    @Override
    public Integer modifyEmployee(Employee employee) {
        Integer changeRowNum = employeeMapper.modifyEmployee(employee);
        return changeRowNum;
    }

    @Override
    public Integer addRoleToEmplyee(EmployeeRoleDTO employeeRoleDTO) {
        Integer addRowNum = employeeMapper.addRoleToEmplyee(employeeRoleDTO);
        return addRowNum;
    }

    @Override
    public Long getRoleByEmployeeId(Long id) {
        Long roleByEmployeeId = employeeMapper.getRoleByEmployeeId(id);
        return roleByEmployeeId;
    }

    @Override
    public Integer modifyRoleOfEmployee(EmployeeRoleDTO employeeRoleDTO) {
        Integer changRowNum = employeeMapper.modifyRoleOfEmployee(employeeRoleDTO);
        return changRowNum;
    }
}
