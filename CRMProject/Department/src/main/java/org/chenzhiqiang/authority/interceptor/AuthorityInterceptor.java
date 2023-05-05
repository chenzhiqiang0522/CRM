package org.chenzhiqiang.authority.interceptor;

import org.chenzhiqiang.authority.annotation.Authority;
import org.chenzhiqiang.authority.domain.Permission;
import org.chenzhiqiang.authority.mapper.PermissionMapper;
import org.chenzhiqiang.domain.Employee;
import org.chenzhiqiang.utils.LoginMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.WebHandler;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
         /*
         * 代码逻辑
         *  1 判断URL请求的Controller是否需要权限才能访问 不需要则放行
         *  2 需要权限
         *      2.1 获取当前用户的全部权限
         *      2.2 获取当前URL所需要的权限
         *      2.3 判断当前URL所需要的权限是否存在于用户权限中
         *          2.3.1 存在则放行
         *          2.3.2 不存在则返回对应的消息，并拦截
         * */
        response.setCharacterEncoding("UTF-8");
        String token = request.getHeader("token");
        Employee loginUser = (Employee) LoginMap.loginMap.get(token);
        System.out.println(handler);
        HandlerMethod handlerMethod = null;
        if (handler instanceof HandlerMethod) {
            handlerMethod = (HandlerMethod) handler;
        }
        //            1 判断URL请求的Controller是否需要权限才能访问 不需要则放行
        String classSimpleName = handlerMethod.getBean().getClass().getSimpleName();
        Method method = handlerMethod.getMethod();
        Authority methodAnnotation = method.getAnnotation(Authority.class);
        if (Objects.isNull(methodAnnotation)){      // 为空则表示没有Authority注解，表示不需要权限，直接放行
            return true;
        }
//            获取当前用户的全部权限
        ArrayList<String> allPermissions = permissionMapper.getAllPermissionsById(loginUser.getId());
//            获取当前URL所需要的权限 URL权限 = 类名:方法名
        String methodName = method.getName();
        String urlPermission = classSimpleName.concat(":").concat(methodName);
//            判断当前URL所需要的权限是否存在于用户权限中
        if (!allPermissions.contains(urlPermission)){
            response.getWriter().print("{\"success\":false,\"msg\":\"缺少操作该功能的权限\"}");
            return false;
        }
        return true;
    }
}
