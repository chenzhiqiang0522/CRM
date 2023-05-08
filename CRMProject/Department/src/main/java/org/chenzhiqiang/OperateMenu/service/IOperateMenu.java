package org.chenzhiqiang.OperateMenu.service;

import io.swagger.models.auth.In;
import org.chenzhiqiang.OperateMenu.domain.OperateMenu;
import org.chenzhiqiang.domain.Department;
import org.chenzhiqiang.domain.DepartmentQueryObject;
import org.chenzhiqiang.utils.QueryObj;

import java.util.ArrayList;
import java.util.List;

public interface IOperateMenu {
    List<OperateMenu> getAllOperateMenu();
    OperateMenu selectOperateMenuByID(Long id);
    Integer deleteOperateMenuById(Long id);
    Long addOperateMenu(OperateMenu operateMenu);
    Integer modifyOperateMenu(OperateMenu operateMenu);
    List<OperateMenu> pageList(QueryObj queryObj);
    List<OperateMenu> seletOperateMenuByQueryObjec(QueryObj queryObj);

    Integer patchDeleteOperateMenus(ArrayList<Long> ids);

    List<OperateMenu> tree(Long loginUserId);
    Integer deleteRoleMenu(Long id);
    Integer addRoleMenu(Long employeeId,List<Long> ids);
    List<Long> getRoleMenu(Long id);
}
