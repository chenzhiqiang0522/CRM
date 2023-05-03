package org.chenzhiqiang.OperateMenu.mapper;

import org.chenzhiqiang.OperateMenu.domain.OperateMenu;
import org.chenzhiqiang.domain.Department;
import org.chenzhiqiang.utils.QueryObj;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OperateMenuMapper {
    List<OperateMenu> selectAll();

    OperateMenu selectByID(Long id);

    Integer deleteById(Long id);

    Long addOperateMenu(OperateMenu operateMenu);

    Integer modifyOperateMenu(OperateMenu operateMenu);

    List<OperateMenu> seletByQueryObjec(QueryObj queryObject);
    List<OperateMenu> pageList(QueryObj queryObj);

    Integer patchDeleteDepartments(ArrayList<Long> ids);
}
