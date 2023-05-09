package org.chenzhiqiang.basic.service;

import org.chenzhiqiang.basic.query.BaseQuery;
import org.chenzhiqiang.utils.PageList;
import org.chenzhiqiang.utils.QueryObj;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> {

    // 新增
    void add(T t);

    // 修改
    void update(T t);

    // 删除
    void removeById(Serializable id);

    // 根据ID查询单条
    T getById(Serializable id);

    // 查询所有
    List<T> getAll();

    PageList<T> pageList(QueryObj queryObj);

    void batchRemove(List<Long> ids);

}