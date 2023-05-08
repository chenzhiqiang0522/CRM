package org.chenzhiqiang.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRoleDTO {
    private Long employeeId;
    private Long roleId;
}
