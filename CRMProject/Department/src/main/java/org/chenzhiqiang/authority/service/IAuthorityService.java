package org.chenzhiqiang.authority.service;

import org.chenzhiqiang.authority.domain.Permission;
import org.chenzhiqiang.utils.QueryObj;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public interface IAuthorityService {
    void scan();
    List<Permission> getAllPermissions();
    List<Permission> getTotalByQueryObj(QueryObj queryObj);
    List<Permission> pageList(QueryObj queryObj);
}
