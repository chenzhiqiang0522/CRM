package org.chenzhiqiang.authority.service.impl;

import org.apache.ibatis.annotations.Param;
import org.chenzhiqiang.authority.domain.Role;
import org.chenzhiqiang.authority.domain.dto.RolePermissionDTO;
import org.chenzhiqiang.authority.mapper.RoleMapper;
import org.chenzhiqiang.authority.service.IRoleService;
import org.chenzhiqiang.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
@Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleMapper.selectAlls();
        return roles;
    }

    @Override
    public List<Role> getAllRoleByQueryObj(QueryObj queryObj) {
        List<Role> roles = roleMapper.selectAllsByQueryObj(queryObj);
        return roles;
    }

    @Override
    public List<Role> pageList(QueryObj queryObj) {
        List<Role> roles = roleMapper.pageList(queryObj);
        return roles;
    }

    @Override
    @Transactional
    public Integer deleteRoleById(Long id) {
        Integer changRowNum = roleMapper.deleteRoleById(id);
        return changRowNum;
    }

    @Override
    @Transactional
    public Integer patchDelete(ArrayList<Long> ids) {
        Integer changRowNum = roleMapper.patchDelete(ids);
        return changRowNum;
    }

    @Transactional
    @Override
    public Integer addRole(Role role) {
        Integer addRowsNum = roleMapper.addRole(role);
        return addRowsNum;
    }

    @Transactional
    @Override
    public Integer updateRole(Role role) {
        Integer changeRowsNum = roleMapper.updateRole(role);
        return changeRowsNum;
    }

    @Transactional
    @Override
    public void setPermission(RolePermissionDTO rolePermissionDTO) {
        roleMapper.deleteRoleByRoleId(rolePermissionDTO.getRoleId());
        roleMapper.savePermission(rolePermissionDTO.getRoleId(),rolePermissionDTO.getPermissionSns());
    }
}
