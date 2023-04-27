package org.chenzhiqiang.authority.service;

import org.chenzhiqiang.authority.domain.Permission;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IAuthorityService {
    void scan();
    List<Permission> getAllPermissions();
}
