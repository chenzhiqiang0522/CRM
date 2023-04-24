package org.chenzhiqiang.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentQueryObject {
    private String keyword;
    private Integer currentPage;
    private Integer pageSize;
}
