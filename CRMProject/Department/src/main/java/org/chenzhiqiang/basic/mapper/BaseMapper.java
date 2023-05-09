package org.chenzhiqiang.basic.mapper;


import org.chenzhiqiang.utils.QueryObj;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
@Repository

public interface BaseMapper<T> {

    // 新增
    void insert(T t);

    // 修改
    void update(T t);

    // 删除
    void deleteById(Serializable id);

    // 根据ID查询单条
    T loadById(Serializable id);

    // 查询所有
    List<T> loadAll();

    void batchDelete(List<Long> ids);

    //======================分页查询===========================
    Integer queryTotal(QueryObj queryObj);

    List<T> queryData(QueryObj queryObj);

    //======================分页查询===========================

}
