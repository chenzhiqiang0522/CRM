package org.chenzhiqiang.OperateMenu.service.impl;

import org.chenzhiqiang.OperateMenu.domain.OperateMenu;
import org.chenzhiqiang.OperateMenu.mapper.OperateMenuMapper;
import org.chenzhiqiang.OperateMenu.service.IOperateMenu;
import org.chenzhiqiang.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class OperateMenuImpl implements IOperateMenu {
    @Autowired
    private OperateMenuMapper operateMenuMapper;
    @Override
    public List<OperateMenu> getAllOperateMenu() {
        List<OperateMenu> operateMenus = operateMenuMapper.selectAll();
        return operateMenus;
    }

    @Override
    public OperateMenu selectOperateMenuByID(Long id) {
        OperateMenu operateMenu = operateMenuMapper.selectByID(id);
        return operateMenu;
    }

    @Override
    public Integer deleteOperateMenuById(Long id) {
        Integer deleteRowNum = operateMenuMapper.deleteById(id);
        return deleteRowNum;
    }

    @Override
    public Long addOperateMenu(OperateMenu operateMenu) {
        Long insertId = operateMenuMapper.addOperateMenu(operateMenu);
        return insertId;
    }

    @Override
    public Integer modifyOperateMenu(OperateMenu operateMenu) {
        Integer updateRowNum = operateMenuMapper.modifyOperateMenu(operateMenu);
        return updateRowNum;
    }

    @Override
    public List<OperateMenu> pageList(QueryObj queryObj) {
        List<OperateMenu> operateMenus = operateMenuMapper.pageList(queryObj);
        return operateMenus;
    }

    @Override
    public List<OperateMenu> seletOperateMenuByQueryObjec(QueryObj queryObj) {
        List<OperateMenu> operateMenus = operateMenuMapper.seletByQueryObjec(queryObj);
        return operateMenus;
    }

    @Override
    public Integer patchDeleteOperateMenus(ArrayList<Long> ids) {
        Integer delRowsNum = operateMenuMapper.patchDeleteDepartments(ids);
        return delRowsNum;
    }

    @Override
    public List<OperateMenu> tree(Long loginUserId) {
        List<OperateMenu> tree = operateMenuMapper.tree(loginUserId);
        return tree;
    }
}
