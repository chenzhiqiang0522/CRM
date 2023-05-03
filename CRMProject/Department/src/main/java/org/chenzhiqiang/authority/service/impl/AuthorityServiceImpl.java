package org.chenzhiqiang.authority.service.impl;

import io.swagger.models.auth.In;
import org.chenzhiqiang.RequestTypeEnum;
import org.chenzhiqiang.authority.annotation.Authority;
import org.chenzhiqiang.authority.domain.Permission;
import org.chenzhiqiang.authority.mapper.PermissionMapper;
import org.chenzhiqiang.authority.service.IAuthorityService;
import org.chenzhiqiang.utils.ClassUtils;
import org.chenzhiqiang.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
        permissionMapper.deleteAll();
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
            parentPermission.setUrl(purl);
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
                String methodUrl = getMethodUrl(method);
                permission.setUrl(purl+methodUrl);
                permission.setSn(aClass.getSimpleName()+":"+method.getName());
                permission.setParentId(parentPermission.getId());
                permissionMapper.insert(permission);
            }
        }
    }

    @Override
    public List<Permission> getAllPermissions() {
        List<Permission> permissions = permissionMapper.selectAll();
        return permissions;
    }

    @Override
    public List<Permission> getTotalByQueryObj(QueryObj queryObj) {
        List<Permission> total = permissionMapper.getTotal(queryObj);
        return total;
    }

    @Override
    public List<Permission> pageList(QueryObj queryObj) {
        List<Permission> permissions = permissionMapper.selectByQueryObj(queryObj);
        return permissions;
    }

    @Override
    public List<Permission> tree() {
        List<Permission> permissionTree = permissionMapper.tree();
        return permissionTree;
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
//        PutMapping putMapping = method.getAnnotation(PutMapping.class);
//        if(Objects.nonNull(putMapping)){
//            methodUrl = putMapping.value() != null && putMapping.value().length > 0 ? putMapping.value()[0] : "";
//        }
//        PostMapping postMapping = method.getAnnotation(PostMapping.class);
//        if(Objects.nonNull(postMapping)){
//            methodUrl = postMapping.value() != null && postMapping.value().length > 0 ? postMapping.value()[0] : "";
//        }
//        GetMapping getMapping = method.getAnnotation(GetMapping.class);
//        if(Objects.nonNull(getMapping)){
//            methodUrl = getMapping.value() != null && getMapping.value().length > 0? getMapping.value()[0] : "";
//        }
//        DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
//        if(Objects.nonNull(deleteMapping)){
//            methodUrl = deleteMapping.value() != null && deleteMapping.value().length > 0 ? deleteMapping.value()[0] : "";
//        }
//        PatchMapping patchMapping = method.getAnnotation(PatchMapping.class);
//        if(Objects.nonNull(patchMapping)){
//            methodUrl = patchMapping.value() != null && patchMapping.value().length > 0 ? patchMapping.value()[0] : "";
//        }
//        return methodUrl;
    }
}
