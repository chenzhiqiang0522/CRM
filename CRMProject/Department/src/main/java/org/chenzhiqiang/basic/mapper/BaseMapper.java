package org.chenzhiqiang.basic.mapper;

import org.chenzhiqiang.utils.QueryObj;

import java.util.List;

public interface BaseMapper<T> {
    List<T> selectAll();
    Integer add(T t);
    Integer deleteById(Long id);
    Integer modify(T t);
    List<T> pageList(QueryObj queryObj);
    List<T> selectAllByQuery(QueryObj queryObj);

}
