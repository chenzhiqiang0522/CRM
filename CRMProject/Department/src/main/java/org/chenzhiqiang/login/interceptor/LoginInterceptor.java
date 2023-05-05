package org.chenzhiqiang.login.interceptor;

import org.chenzhiqiang.domain.Employee;
import org.chenzhiqiang.mapper.EmployeeMapper;
import org.chenzhiqiang.utils.LoginMap;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Objects;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private EmployeeMapper employeeMapper;
    //前置拦截器
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String token = request.getHeader("token");
        Object loginUser = LoginMap.loginMap.get(token);
        PrintWriter outPut = null;
        if (StringUtils.isEmpty(token)){
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("success",false);
//            jsonObject.put("msg","noLogin");
//            outPut = response.getWriter();
//            outPut.append(jsonObject.toString());
            response.getWriter().print("{\"success\":false,\"msg\":\"noLogin\"}");
            return false;
        }
        if (Objects.isNull(loginUser)){
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("success",false);
//            jsonObject.put("msg","noLogin");
//            outPut = response.getWriter();
//            outPut.append(jsonObject.toString());
            response.getWriter().print("{\"success\":false,\"msg\":\"noLogin\"}");
            return false;
        }
        return true;
    }

}
