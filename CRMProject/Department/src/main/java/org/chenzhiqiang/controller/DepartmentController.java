package org.chenzhiqiang.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chenzhiqiang.domain.Department;
import org.chenzhiqiang.domain.DepartmentQueryObject;
import org.chenzhiqiang.services.impl.DepartmentServiceImpl;
import org.chenzhiqiang.utils.PageList;
import org.chenzhiqiang.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/Departments")
@Api(value = "部门的接口",description="部门相关的CRUD功能")
public class DepartmentController {
    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;

    public static ReturnResult errorMethod(Exception e,ReturnResult r){
        e.printStackTrace();
        r.setSuccess(Boolean.valueOf("false"));
        r.setMsg("执行失败");
        r.setResultObj(e.getMessage());
        System.out.println(r);
        return r;
    }
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询全部部门" )
    public ReturnResult getAll(){
        ReturnResult returnResult = new ReturnResult();
        List<Department> allDepartment = null;
        try {
            allDepartment = departmentServiceImpl.getAllDepartments();
            returnResult.setResultObj(allDepartment);
            System.out.println(returnResult);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e,returnResult);
        }
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    @ResponseBody
    @ApiOperation(value = "通过ID查询部门信息" )
    public ReturnResult getDepartmentByID(@PathVariable("id")Long id){
        ReturnResult returnResult = new ReturnResult();
        try {
            Department department = departmentServiceImpl.selectDepartmentByID(id);
            if (Objects.isNull(department)){
                returnResult.setMsg("数据库中没有对应id的部门");
                returnResult.setResultObj(department);
            }
            returnResult.setResultObj(department);
            System.out.println(returnResult);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e,returnResult);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    @ResponseBody
    @ApiOperation(value = "通过ID或者名字删除部门信息" )
    public ReturnResult deleteDepartmentById(@PathVariable("id")Object id){
        System.out.println(id);
        ReturnResult returnResult = new ReturnResult();
        Integer changedRowNumber = 0;
        try {
            if (id instanceof Number){
                Long idl = (Long)id;
                changedRowNumber = departmentServiceImpl.deleteDepartmentById(idl);
            }else{
                String idS = (String)id;
                changedRowNumber = departmentServiceImpl.deleteDepartmentByName(idS);
            }
            if (changedRowNumber==0){
                returnResult.setMsg("此操作没有更改数据库");
                returnResult.setResultObj(null);
            }
            returnResult.setResultObj("此操作更改了数据库中"+changedRowNumber+"行数据");
            System.out.println(returnResult);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e,returnResult);
        }
    }

    @RequestMapping(method = RequestMethod.POST,value = "/add")
    @ApiOperation(value = "通过增加和修改部门信息" )
    public ReturnResult addAndModifyDepartment(@RequestBody Department department){
        System.out.println(department);
        ReturnResult returnResult = new ReturnResult();
        Number changedRowNumber = null;
        try {
            if (Objects.isNull(department.getId())){
                changedRowNumber = departmentServiceImpl.addDepartment(department);
            }else{
                changedRowNumber = departmentServiceImpl.modifyDepartment(department);
            }
            if (Objects.isNull(changedRowNumber)){
                returnResult.setMsg("此操作没有更改数据库");
            }
            System.out.println(returnResult);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e,returnResult);
        }
    }

    @RequestMapping(method = RequestMethod.POST,value = "/pageList")
    @ResponseBody
    public ReturnResult pageList(@RequestBody DepartmentQueryObject queryObject){
        System.out.println(queryObject);
        PageList<Department> departmentPageList = new PageList<>();
        ReturnResult returnResult = new ReturnResult();
        try {
            List<Department> departments = departmentServiceImpl.pageList(queryObject);
            departmentPageList.setTotal(departments.size());
            departmentPageList.setRows(departments);
            returnResult.setResultObj(departmentPageList);
            System.out.println(departments);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e,returnResult);
        }
    }

}
