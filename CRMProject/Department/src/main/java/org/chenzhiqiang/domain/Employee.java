package org.chenzhiqiang.domain;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String headImage;
    private Integer age;
    private Long departmentId;
    private String parentDepartmentName;

    public Employee(String username, String password, String email, String headImage, Integer age, Long departmentId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.headImage = headImage;
        this.age = age;
        this.departmentId = departmentId;
    }
}
