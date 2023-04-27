package org.chenzhiqiang.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryObj {
    private String keyword;
    private Integer currentPage;
    private Integer pageSize;
    public Integer getStart(){
        return (this.currentPage - 1) * pageSize;
    }
}
