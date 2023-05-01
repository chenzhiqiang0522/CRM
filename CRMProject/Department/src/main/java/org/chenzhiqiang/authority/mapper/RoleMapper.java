package org.chenzhiqiang.authority.mapper;

import org.chenzhiqiang.authority.domain.Role;
import org.chenzhiqiang.utils.QueryObj;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public interface RoleMapper {
    List<Role> selectAlls();
    List<Role> selectAllsByQueryObj(QueryObj queryObj);
    List<Role> pageList(QueryObj queryObj);
    Integer deleteRoleById(Long id);
    Integer patchDelete(ArrayList<Long> ids);
    Integer addRole(Role role);
    Integer updateRole(Role role);


}
