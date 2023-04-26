package org.chenzhiqiang.authority.linstener;

import org.chenzhiqiang.authority.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
@Component
public class InintListener implements ServletContextListener {
    @Autowired
    private IAuthorityService iAuthorityService;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("扫描所有权限注解。。。。。。。。。。。。。。。。。。。。。");
        iAuthorityService.scan();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
