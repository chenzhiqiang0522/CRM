package org.chenzhiqiang.login.service.impl;

import org.chenzhiqiang.domain.Employee;
import org.chenzhiqiang.login.dto.LoginDTO;
import org.chenzhiqiang.login.service.ILoginService;
import org.chenzhiqiang.mapper.EmployeeMapper;
import org.chenzhiqiang.utils.LoginMap;
import org.chenzhiqiang.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
* @author : NickWiber(陈志强)
* @date : 2023/5/4 14:23
* @description : 将登录Token存入全局map中，并分别返回Token和Employee对象
* @others:
*/
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Map<String, Object> getToken(LoginDTO loginDTO) {
        String username = loginDTO.getUsername();
        String password = loginDTO.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new RuntimeException("用户名和密码不能为空");
        }
        Employee employee = employeeMapper.selectAllByUsername(username);
        if (Objects.isNull(employee)){
            throw new RuntimeException("用户不存在，请先联系管理员注册");
        }
        if (!(employee.getPassword().equals(password))){
            throw new RuntimeException("用户名或者密码错误");
        }
        String uuid = UUID.randomUUID().toString().replace("-","");
        String token = uuid.concat(username);
        employee.setPassword("");       // 删除密码字段
        LoginMap.loginMap.put(token,employee);      //  存入全局map中
        HashMap<String, Object> returnToBrowerMap = new HashMap<>();
        returnToBrowerMap.put("token",token);
        returnToBrowerMap.put("loginUser",employee);
        return returnToBrowerMap;
    }
}
