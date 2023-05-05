package org.chenzhiqiang.login.service;

import org.chenzhiqiang.login.dto.LoginDTO;

import java.util.Map;

public interface ILoginService {
    public Map<String,Object> getToken(LoginDTO loginDTO);
}
