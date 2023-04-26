package org.chenzhiqiang.authority.mapper;

import org.chenzhiqiang.authority.domain.Permission;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper {
    void insert(Permission parent);
    void deleteAll();
}
