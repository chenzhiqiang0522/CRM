package org.chenzhiqiang.authority.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chenzhiqiang.authority.annotation.Authority;
import org.chenzhiqiang.authority.domain.Permission;
import org.chenzhiqiang.authority.service.IAuthorityService;
import org.chenzhiqiang.utils.PageList;
import org.chenzhiqiang.utils.QueryObj;
import org.chenzhiqiang.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "权限列表",description = "权限相关的CRUD功能")
@RequestMapping(value = "/Permissions")
@Authority(name = "权限列表")
public class AuthorityController {
    @Autowired
    private IAuthorityService authorityServiceImpl;
    @Autowired
    private ReturnResult returnResult;

    public ReturnResult errorMethod(Exception e, ReturnResult r) {
        e.printStackTrace();
        r.setSuccess(Boolean.valueOf("false"));
        r.setMsg("执行失败");
        r.setResultObj(e.getMessage());
        System.out.println(r);
        return r;
    }

    @GetMapping
    @ApiOperation(value = "获取全部权限")
    @Authority(name = "获取全部权限",descs = "获取全部权限")
    public ReturnResult getAll(){
        try {
            List<Permission> allPermissions = authorityServiceImpl.getAllPermissions();
            returnResult.setResultObj(allPermissions);
            return returnResult;
        } catch (Exception e) {
            return errorMethod(e,returnResult);
        }
    }

    @PostMapping("/pageList")
    @ApiOperation(value = "权限分页高级查询")
    @Authority(name = "权限分页高级查询",descs = "权限分页高级查询")
    public ReturnResult pageList(@RequestBody QueryObj queryObj){
        PageList<Permission> permissionPageList = new PageList<>();
        List<Permission> totalByQueryObj = authorityServiceImpl.getTotalByQueryObj(queryObj);
        permissionPageList.setTotal(totalByQueryObj.size());
        List<Permission> permissions1 = authorityServiceImpl.pageList(queryObj);
        permissionPageList.setRows(permissions1);
        returnResult.setResultObj(permissionPageList);
        return returnResult;
    }
}
