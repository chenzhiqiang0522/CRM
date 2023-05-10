package org.chenzhiqiang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chenzhiqiang.authority.annotation.Authority;
import org.chenzhiqiang.domain.Department;
import org.chenzhiqiang.domain.DepartmentQueryObject;
import org.chenzhiqiang.services.impl.DepartmentServiceImpl;
import org.chenzhiqiang.utils.PageList;
import org.chenzhiqiang.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/Departments")
@Api(value = "部门的接口", description = "部门相关的CRUD功能")
@Authority(name = "部门管理")
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    public static ReturnResult errorMethod(Exception e, ReturnResult r) {
        e.printStackTrace();
        r.setSuccess(Boolean.valueOf("false"));
        r.setMsg("执行失败");
        r.setResultObj(e.getMessage());
        System.out.println(r);
        return r;
    }

//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
    @GetMapping
    @ApiOperation(value = "查询全部部门")
    @Authority(name = "获取全部部门",descs = "获取全部部门")
    public ReturnResult getAll() {
        ReturnResult returnResult = new ReturnResult();
        List<Department> allDepartment = null;
        try {
            allDepartment = departmentServiceImpl.getAllDepartments();
            returnResult.setResultObj(allDepartment);
            System.out.println(returnResult);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e, returnResult);
        }
    }

//    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
//    @ResponseBody
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "通过ID查询部门信息")
    @Authority(name = "通过ID查询部门信息")
    public ReturnResult getDepartmentByID(@PathVariable("id") Long id) {
        ReturnResult returnResult = new ReturnResult();
        try {
            Department department = departmentServiceImpl.selectDepartmentByID(id);
            if (Objects.isNull(department)) {
                returnResult.setMsg("数据库中没有对应id的部门");
                returnResult.setResultObj(department);
            }
            returnResult.setResultObj(department);
            System.out.println(returnResult);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e, returnResult);
        }
    }

//    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
//    @ResponseBody
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "通过ID删除部门信息")
    @Authority(name = "通过ID删除部门信息")
    public ReturnResult deleteDepartmentById(@PathVariable("id") Long id) {
//        System.out.println(id);
        ReturnResult returnResult = new ReturnResult();
        Integer changedRowNumber = 0;
        try {
            changedRowNumber = departmentServiceImpl.deleteDepartmentById(id);
            if (changedRowNumber == 0) {
                returnResult.setMsg("此操作没有更改数据库");
                returnResult.setResultObj(null);
            }
            returnResult.setResultObj("此操作更改了数据库中" + changedRowNumber + "行数据");
            System.out.println(returnResult);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e, returnResult);
        }
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/add")
//    @ResponseBody
    @PostMapping(value = "/add")
    @ApiOperation(value = "通过增加和修改部门信息")
    @Authority(name = "增加和修改部门信息")
    public ReturnResult addAndModifyDepartment(@RequestBody Department department) {
//        System.out.println(department);
        ReturnResult returnResult = new ReturnResult();
        Number changedRowNumber = null;
        try {
            if (Objects.isNull(department.getId())) {
                changedRowNumber = departmentServiceImpl.addDepartment(department);
            } else {
                changedRowNumber = departmentServiceImpl.modifyDepartment(department);
            }
            if (Objects.isNull(changedRowNumber)) {
                returnResult.setMsg("此操作没有更改数据库");
            }
            System.out.println(returnResult);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e, returnResult);
        }
    }

//    @RequestMapping(method = RequestMethod.POST, value = "/pageList")
    @PostMapping(value = "/pageList")
//    @ResponseBody
    @Authority(name = "高级查询",descs = "高级查询")
    public ReturnResult pageList(@RequestBody DepartmentQueryObject queryObject) {
        System.out.println(queryObject);
        PageList<Department> departmentPageList = new PageList<>();
        ReturnResult returnResult = new ReturnResult();
        try {
            List<Department> departments = departmentServiceImpl.getAllDepartments();
            departmentPageList.setTotal(departments.size());
            departmentPageList.setRows(departmentServiceImpl.pageList(queryObject));
            returnResult.setResultObj(departmentPageList);
//            System.out.println(departments);
//            System.out.println("===============================");
//            System.out.println(departmentPageList.getRows());
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e, returnResult);
        }
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/patchDelete")
    @ResponseBody
    @Authority(name = "批量删除",descs = "批量删除部门")
    public ReturnResult patchDeleteDepartments(@RequestBody ArrayList<Long> ids){
        ReturnResult returnResult = new ReturnResult();
        System.out.println("批量删除参数"+ids);
        Integer integer;
        try {
            integer = departmentServiceImpl.patchDeleteDepartments(ids);
            returnResult.setMsg("批量删除了"+integer+"条数据");
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e, returnResult);
        }
    }
    @RequestMapping(method = RequestMethod.GET, value = "/departmentTree")
    @ResponseBody
    @Authority(name = "部门树",descs = "获取全部的以及部门以及该部门的子部门")
    public ReturnResult getDepartmentTree(){
        ReturnResult returnResult = new ReturnResult();
        try {
            List<Department> departmentsTree = departmentServiceImpl.getChildDepartments();
            returnResult.setResultObj(departmentsTree);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e, returnResult);
        }
    }
}


