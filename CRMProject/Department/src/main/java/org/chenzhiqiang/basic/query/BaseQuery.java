package org.chenzhiqiang.basic.query;

import lombok.Data;

@Data
public class BaseQuery {

    private Integer currentPage=1;    // 当前要查询的页数
    private Integer pageSize=10;   // 每页要显示的条数
    private String keyword;
    public Integer getStart(){
        return (this.currentPage-1)*this.pageSize;
    }

}