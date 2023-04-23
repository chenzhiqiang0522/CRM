package org.chenzhiqiang.controller;

import org.chenzhiqiang.domain.Department;
import org.chenzhiqiang.services.IDepartmentService;
import org.chenzhiqiang.services.impl.DepartmentServiceImpl;
import org.chenzhiqiang.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;
    @RequestMapping(method = RequestMethod.GET,value = "/Departments")
    public ReturnResult getAll(){
        ReturnResult returnResult = new ReturnResult();
        List<Department> allDepartment = null;
        try {
            allDepartment = departmentServiceImpl.getAllDepartments();
            System.out.println(allDepartment);
            returnResult.setResultObj(allDepartment);
            return returnResult;
        } catch (Exception e) {
            e.printStackTrace();
            returnResult.setSuccess(Boolean.valueOf("false"));
            returnResult.setMsg("执行失败");
            returnResult.setResultObj(e.getMessage());
            return returnResult;
        }
    }
}
