package org.chenzhiqiang.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Department {
    private Long id;
    private String name;
    private String intro;
    @JsonFormat()
    private Date createTime;
    private Date updateTime;
    private Long managerId;
    private Long parentId;
    private String path;
    private Integer state;
    private Employee manager;   // 部门经理

    private Department parent;  // 父部门
    private List<Department> childDepartments;


}
