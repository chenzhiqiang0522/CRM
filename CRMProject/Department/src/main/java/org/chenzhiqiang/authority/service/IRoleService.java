package org.chenzhiqiang.authority.service;

import org.chenzhiqiang.authority.domain.Role;
import org.chenzhiqiang.utils.QueryObj;

import java.util.ArrayList;
import java.util.List;

public interface IRoleService {
    List<Role> getAllRoles();
    List<Role> getAllRoleByQueryObj(QueryObj queryObj);
    List<Role> pageList(QueryObj queryObj);
    Integer deleteRoleById(Long id);
    Integer patchDelete(ArrayList<Long> ids);
    Integer addRole(Role role);
    Integer updateRole(Role role);
}
