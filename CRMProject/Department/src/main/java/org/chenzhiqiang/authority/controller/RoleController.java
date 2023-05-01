package org.chenzhiqiang.authority.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chenzhiqiang.authority.annotation.Authority;
import org.chenzhiqiang.authority.domain.Role;
import org.chenzhiqiang.authority.service.IRoleService;
import org.chenzhiqiang.utils.PageList;
import org.chenzhiqiang.utils.QueryObj;
import org.chenzhiqiang.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Authority(name = "角色列表",descs = "角色的CRUD相关操作")
@Api(value = "角色列表",description = "角色的CRUD相关操作")
@RequestMapping(value = "/Roles")
public class RoleController {
    @Autowired
    private IRoleService roleServiceImpl;
    @Autowired
    private ReturnResult returnResult;
    @GetMapping
    @ApiOperation(value = "获取全部角色")
    @Authority(name = "获取全部角色",descs = "获取全部角色")
    public ReturnResult getAllRoles(){
        List<Role> allRoles = roleServiceImpl.getAllRoles();
        returnResult.setResultObj(allRoles);
        return returnResult;
    }

    @PostMapping
    @ApiOperation(value = "使用高级查询角色")
    @Authority(name = "使用高级查询角色",descs = "使用高级查询角色")
    public ReturnResult getAllRolesByQueryObj(@RequestBody  QueryObj obj){
        List<Role> allRoles = roleServiceImpl.getAllRoleByQueryObj(obj);
        returnResult.setResultObj(allRoles);
        return returnResult;
    }

    @PostMapping("/pageList")
    @ApiOperation(value = "使用高级+分页查询角色")
    @Authority(name = "使用高级+分页查询角色",descs = "使用高级+分页查询角色")
    public ReturnResult pageList(@RequestBody QueryObj queryObj){
        PageList<Role> rolePageList = new PageList<>();
        List<Role> allRoles = roleServiceImpl.getAllRoleByQueryObj(queryObj);
        rolePageList.setTotal(allRoles.size());
        List<Role> roles = roleServiceImpl.pageList(queryObj);
        rolePageList.setRows(roles);
        returnResult.setResultObj(rolePageList);
        return returnResult;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据id删除角色")
    @Authority(name = "根据id删除角色", descs = "根据id删除角色")
    public ReturnResult deleteRoleById(@PathVariable("id") Long id){
        Integer deleteRowsNum = roleServiceImpl.deleteRoleById(id);
        returnResult.setMsg("删除了"+deleteRowsNum+"行数据。");
        return returnResult;
    }

    @PatchMapping("/patchDeleteRoles")
    @ApiOperation(value = "批量删除角色")
    @Authority(name = "批量删除角色", descs = "批量删除角色")
    public ReturnResult patchDeleteRoles(@RequestBody ArrayList<Long> ids){
        Integer deleteRowsNum = roleServiceImpl.patchDelete(ids);
        returnResult.setMsg("删除了"+deleteRowsNum+"行数据。");
        return returnResult;
    }

    @PostMapping(value = "/upadateRole")
    @Authority(name = "修改角色", descs = "修改角色")
    @ApiOperation(value = "修改角色")
    public ReturnResult updateRole(@RequestBody Role role){
        Integer changeRowsNum = roleServiceImpl.updateRole(role);
        returnResult.setMsg("修改了"+changeRowsNum+"行数据");
        return returnResult;
    }

    @PostMapping(value = "/addRole")
    @Authority(name = "新增角色",descs = "新增角色")
    @ApiOperation(value = "新增角色")
    public ReturnResult addRole(@RequestBody Role role){
        Integer insertRowsNum = roleServiceImpl.addRole(role);
        returnResult.setMsg("修改了"+insertRowsNum+"行数据");
        return returnResult;
    }
}
