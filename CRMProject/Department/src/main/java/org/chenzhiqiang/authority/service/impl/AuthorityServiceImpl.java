package org.chenzhiqiang.authority.service.impl;

import org.chenzhiqiang.RequestTypeEnum;
import org.chenzhiqiang.authority.annotation.Authority;
import org.chenzhiqiang.authority.domain.Permission;
import org.chenzhiqiang.authority.mapper.PermissionMapper;
import org.chenzhiqiang.authority.service.IAuthorityService;
import org.chenzhiqiang.utils.ClassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

@Service
public class AuthorityServiceImpl implements IAuthorityService {
    @Value("${authority.package}")
    private String scanPackage;
    @Autowired
    private PermissionMapper permissionMapper;
    @Override
    public void scan() {
//        System.out.println(scanPackage);
        List<Class> classes = ClassUtils.getAllClassName(this.scanPackage);
        for (Class aClass : classes) {
            Authority annotation = (Authority)aClass.getAnnotation(Authority.class);
            if (Objects.isNull(annotation))
                continue;
            RequestMapping pannotation = (RequestMapping)aClass.getAnnotation(RequestMapping.class);
            String purl = pannotation.value()[0];
            Permission parentPermission = new Permission();
            parentPermission.setName(annotation.name());
            parentPermission.setDescs(annotation.descs());
            parentPermission.setUrl(purl);        // 一级权限的url,就是类上面的那个url
            parentPermission.setSn(aClass.getSimpleName());
            permissionMapper.insert(parentPermission);
            Method[] methods = aClass.getMethods();
            for (Method method : methods) {
                Authority annotation1 = method.getAnnotation(Authority.class);
                if (Objects.isNull(annotation1))
                    continue;
                Permission permission = new Permission();
                permission.setName(method.getName());
                permission.setDescs(annotation1.descs());
                permission.setUrl(purl+getMethodUrl(method));
                permission.setSn(aClass.getSimpleName()+":"+method.getName());
                permissionMapper.insert(permission);
            }
        }
    }

    private String getMethodUrl(Method method){
        String methodUrl = "";
        RequestTypeEnum[] requestTypeEnums = RequestTypeEnum.values();
        for (RequestTypeEnum requestTypeEnum : requestTypeEnums) {
            Annotation annotation = method.getAnnotation(requestTypeEnum.getRequestType());
            if (Objects.isNull(annotation))
                continue;
            try {
                Method annotationMethod = annotation.annotationType().getMethod("value");
                String[] value = (String[]) annotationMethod.invoke(annotation);
                if(value != null && value.length > 0){
                    methodUrl = value[0];
                    break;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return methodUrl;
    }
}
