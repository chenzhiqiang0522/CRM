package org.chenzhiqiang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chenzhiqiang.domain.Employee;
import org.chenzhiqiang.services.impl.EmployeeServiceImpl;
import org.chenzhiqiang.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Employees")
@Api(value = "员工的接口", description = "员工相关的CRUD功能")
public class EmployeeController {
    @Autowired
    private EmployeeServiceImpl employeeService;
    @Autowired
    private ReturnResult returnResult;

    public static ReturnResult errorMethod(Exception e, ReturnResult r) {
        e.printStackTrace();
        r.setSuccess(Boolean.valueOf("false"));
        r.setMsg("执行失败");
        r.setResultObj(e.getMessage());
        System.out.println(r);
        return r;
    }
    @RequestMapping
    @ApiOperation(value = "获取全部员工")
    public ReturnResult getAll(){

        try {
            List<Employee> allEmployees = employeeService.getAllEmployees();
            returnResult.setResultObj(allEmployees);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }

    }
}
