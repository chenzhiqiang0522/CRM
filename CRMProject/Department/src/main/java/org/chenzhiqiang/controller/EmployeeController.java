package org.chenzhiqiang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chenzhiqiang.authority.annotation.Authority;
import org.chenzhiqiang.domain.Employee;
import org.chenzhiqiang.dto.EmployeeRoleDTO;
import org.chenzhiqiang.services.impl.EmployeeServiceImpl;
import org.chenzhiqiang.utils.PageList;
import org.chenzhiqiang.utils.QueryObj;
import org.chenzhiqiang.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/Employees")
@Api(value = "员工的接口", description = "员工相关的CRUD功能")
@Authority(name = "员工管理")
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
    @Authority(name = "获取全部员工",descs = "获取全部员工")
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

    @PostMapping(value = "/pageList")
    @ApiOperation(value = "高级查询")
    @Authority(name = "高级查询",descs = "高级查询")
    public ReturnResult pageList(@RequestBody QueryObj queryObj){
        PageList<Employee> pageList = new PageList<>();
        try {
            List<Employee> employees = employeeService.selectByQueryObj(queryObj);
            int total = employeeService.getAllEmployees().size();
            pageList.setTotal(total);
            pageList.setRows(employees);
            returnResult.setResultObj(pageList);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }

    }

    @PostMapping(value = "/addEmployee")
    @ApiOperation(value = "添加新员工")
    @Authority(name = "添加新员工",descs = "添加新员工")
    public ReturnResult addEmployee(@RequestBody Employee employee){
        try {
            Long inserId = employeeService.addEmployee(employee);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }

    }

    @DeleteMapping(value = "/deleteById/{id}")
    @ApiOperation(value = "删除员工")
    @Authority(name = "删除员工",descs = "删除员工")
    public ReturnResult deleteById(@PathVariable Long id){
        try {
            Integer delRowNum = employeeService.deleteById(id);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }

    }

    @PatchMapping(value = "/patchDelete")
    @ApiOperation(value = "批量删除员工")
    @Authority(name = "批量删除员工",descs = "批量删除员工")
    public ReturnResult batchDelete(@RequestBody ArrayList<Long> ids){
        try {
            Integer delRowsNum = employeeService.batchDelete(ids);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }

    }

    @PostMapping(value = "/modifyEmployee")
    @ApiOperation(value = "修改员工信息")
    @Authority(name = "修改员工信息",descs = "修改员工信息")
    public ReturnResult modifyEmployee(@RequestBody Employee employee){
        try {
            Integer changRowNum = employeeService.modifyEmployee(employee);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }

    }

    @PostMapping(value = "/addRoleToEmplyee")
    @ApiOperation(value = "设置员工角色")
    @Authority(name = "设置员工角色",descs = "设置员工角色")
    public ReturnResult addRoleToEmplyee(@RequestBody EmployeeRoleDTO employeeRoleDTO){
        try {
            Long roleByEmployeeId = employeeService.getRoleByEmployeeId(employeeRoleDTO.getEmployeeId());
            if (Objects.isNull(roleByEmployeeId)) {
                Integer integer = employeeService.addRoleToEmplyee(employeeRoleDTO);
            } else {
                Integer integer = employeeService.modifyRoleOfEmployee(employeeRoleDTO);
            }
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }
    }

    @GetMapping(value = "/getRoleByEmployeeId/{id}")
    @ApiOperation(value = "获取员工角色id")
    @Authority(name = "获取员工角色id",descs = "获取员工角色id")
    public ReturnResult getRoleByEmployeeId(@PathVariable Long id){
        try {
            Long roleId = employeeService.getRoleByEmployeeId(id);
            returnResult.setResultObj(roleId);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }
    }
}
