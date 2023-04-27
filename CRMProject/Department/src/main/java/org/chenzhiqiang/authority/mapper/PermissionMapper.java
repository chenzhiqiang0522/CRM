package org.chenzhiqiang.authority.mapper;

import org.chenzhiqiang.authority.domain.Permission;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    void insert(Permission parent);
    void deleteAll();

    List<Permission> selectAll();
}
