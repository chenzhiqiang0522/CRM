package org.chenzhiqiang.mapper;

import org.chenzhiqiang.domain.Department;
import org.chenzhiqiang.domain.DepartmentQueryObject;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface DepartmentMapper {
    List<Department> selectAll();
    Department selectByID(Long id);
    Integer deleteByName(String depname);
    Integer deleteById(Long id);
    Long addDepartment(Department department);
    Integer modifyDepartment(Department department);
//    ================== 高级查询（通过关键字查询）=======================
    List<Department> seletByQueryObjec(DepartmentQueryObject queryObject);
//    ==================批量删除=======================
    Integer patchDeleteDepartments(ArrayList<Long> ids);
}
