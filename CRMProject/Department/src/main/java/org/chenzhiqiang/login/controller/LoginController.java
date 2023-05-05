package org.chenzhiqiang.login.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.chenzhiqiang.login.dto.LoginDTO;
import org.chenzhiqiang.login.service.ILoginService;
import org.chenzhiqiang.utils.LoginMap;
import org.chenzhiqiang.utils.ReturnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@Api(value = "获取登录DTO对象",description = "获取登录DTO对象")
public class LoginController {
    @Autowired
    private ILoginService loginServiceImpl;

    public static ReturnResult errorMethod(Exception e, ReturnResult r) {
        e.printStackTrace();
        r.setSuccess(Boolean.valueOf("false"));
        r.setMsg("执行失败");
        r.setResultObj(e.getMessage());
        System.out.println(r);
        return r;
    }
    @PostMapping("/login")
    @ApiOperation(value = "登录后端操作")
    public ReturnResult login(@RequestBody LoginDTO loginDTO){
        ReturnResult returnResult = new ReturnResult();
        try {
            Map<String, Object> tokenAndEmployee = loginServiceImpl.getToken(loginDTO);
            returnResult.setResultObj(tokenAndEmployee);
            System.out.println("loginMap的长度"+LoginMap.loginMap.size());
            System.out.println(returnResult);
            return returnResult;
        } catch (Exception e) {
            errorMethod(e,returnResult);
            return returnResult;
        }
    }

    @PostMapping("/logOut")
    @ApiOperation(value = "登出操作")
    public ReturnResult loginOut(HttpServletRequest request){
        ReturnResult returnResult = new ReturnResult();
        try {
            String token = request.getHeader("token");
            System.out.println(token);
            LoginMap.loginMap.remove(token);
            System.out.println("loginMap的长度"+LoginMap.loginMap.size());
            return returnResult;
        } catch (Exception e) {
            returnResult.setSuccess(false);
            returnResult.setMsg("登出失败");
            return returnResult;
        }

    }

}
