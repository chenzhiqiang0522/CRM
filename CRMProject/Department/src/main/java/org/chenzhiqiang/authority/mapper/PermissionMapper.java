package org.chenzhiqiang.authority.mapper;

import org.chenzhiqiang.authority.domain.Permission;
import org.chenzhiqiang.utils.QueryObj;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PermissionMapper {
    void insert(Permission parent);
    void deleteAll();
    Integer patchDelete(ArrayList<Long> ids);
    Integer deleteById(Long id);
    List<Permission> selectAll();
    List<Permission> getTotal(QueryObj queryObj);

    List<Permission> selectByQueryObj(QueryObj queryObj);
    List<Permission> tree();
}
