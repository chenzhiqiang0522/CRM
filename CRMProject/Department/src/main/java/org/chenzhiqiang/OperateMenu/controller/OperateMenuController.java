package org.chenzhiqiang.OperateMenu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chenzhiqiang.OperateMenu.domain.OperateMenu;
import org.chenzhiqiang.OperateMenu.service.IOperateMenu;
import org.chenzhiqiang.authority.annotation.Authority;
import org.chenzhiqiang.domain.Employee;
import org.chenzhiqiang.services.IDepartmentService;
import org.chenzhiqiang.services.impl.EmployeeServiceImpl;
import org.chenzhiqiang.utils.PageList;
import org.chenzhiqiang.utils.QueryObj;
import org.chenzhiqiang.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
* @author : NickWiber(陈志强)
* @date : 2023/5/2 22:58
* @description : 菜单接口文件
* @others:
*/
@RestController
@RequestMapping("/OperateMenu")
@Api(value = "菜单的接口", description = "菜单的CRUD")
@Authority(name = "菜单的接口")
public class OperateMenuController {
    @Autowired
    private IOperateMenu operateMenuImpl;
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
    @ApiOperation(value = "获取菜单")
    @Authority(name = "获取菜单",descs = "获取菜单")
    public ReturnResult getAll(){
        try {
            List<OperateMenu> allOperateMenus = operateMenuImpl.getAllOperateMenu();
            returnResult.setResultObj(allOperateMenus);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }

    }

    @PostMapping(value = "/pageList")
    @ApiOperation(value = "菜单高级查询")
    @Authority(name = "菜单高级查询",descs = "菜单高级查询")
    public ReturnResult pageList(@RequestBody QueryObj queryObj){
        PageList<OperateMenu> pageList = new PageList<>();
        try {
            List<OperateMenu> allOperateMenus = operateMenuImpl.seletOperateMenuByQueryObjec(queryObj);
            int total = allOperateMenus.size();
            pageList.setTotal(total);
            List<OperateMenu> operateMenus = operateMenuImpl.pageList(queryObj);
            pageList.setRows(operateMenus);
            returnResult.setResultObj(pageList);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }
    }

    @PostMapping(value = "/addOperateMenu")
    @ApiOperation(value = "添加新菜单")
    @Authority(name = "添加新菜单",descs = "添加新菜单")
    public ReturnResult addOperateMenu(@RequestBody OperateMenu operateMenu){
        try {
            Long newOperateMenuId = operateMenuImpl.addOperateMenu(operateMenu);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }
    }

    @DeleteMapping(value = "/deleteById/{id}")
    @ApiOperation(value = "删除菜单")
    @Authority(name = "删除菜单",descs = "删除菜单")
    public ReturnResult deleteById(@PathVariable Long id){
        try {
            Integer delRowNum = operateMenuImpl.deleteOperateMenuById(id);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }
    }

    @PatchMapping(value = "/patchDelete")
    @ApiOperation(value = "批量删除菜单")
    @Authority(name = "批量删除菜单",descs = "批量删除菜单")
    public ReturnResult batchDelete(@RequestBody ArrayList<Long> ids){
        try {
            Integer delRowsNum = operateMenuImpl.patchDeleteOperateMenus(ids);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }
    }

    @PostMapping(value = "/modifyOperateMenu")
    @ApiOperation(value = "修改菜单信息")
    @Authority(name = "修改菜单信息",descs = "修改菜单信息")
    public ReturnResult modifyOperateMenu(@RequestBody OperateMenu operateMenu){
        try {
            Integer updateRowNum = operateMenuImpl.modifyOperateMenu(operateMenu);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }

    }

    @GetMapping(value = "/tree/{loginUserId}")
    @Authority(name = "tree",descs = "获取一级菜单以及其二级菜单")
    @ApiOperation(value = "获取一级菜单以及其二级菜单")
    public ReturnResult tree(@PathVariable Long loginUserId){
        try {
            List<OperateMenu> tree = operateMenuImpl.tree(loginUserId);
            returnResult.setResultObj(tree);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }
    }

}
