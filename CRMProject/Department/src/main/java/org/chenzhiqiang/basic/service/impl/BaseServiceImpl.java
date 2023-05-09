package org.chenzhiqiang.basic.service.impl;

import org.chenzhiqiang.basic.mapper.BaseMapper;
import org.chenzhiqiang.basic.service.IBaseService;
import org.chenzhiqiang.utils.PageList;
import org.chenzhiqiang.utils.QueryObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T> implements IBaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    @Transactional
    @Override
    public void add(T t) {
        baseMapper.insert(t);
    }

    @Transactional
    @Override
    public void update(T t) {
        baseMapper.update(t);
    }

    @Transactional
    @Override
    public void removeById(Serializable id) {
        baseMapper.deleteById(id);
    }

    @Override
    public T getById(Serializable id) {
        return baseMapper.loadById(id);
    }

    @Override
    public List<T> getAll() {
        return baseMapper.loadAll();
    }

    @Override
    public PageList<T> pageList(QueryObj queryObj) {
        // 1 调用mapper查询总条数
        Integer total = baseMapper.queryTotal(queryObj);
        // 2 判断如果总条数 > 0,调用mapper查询当前页数据
        if(total > 0){
            // 3 将查询出的结果封装为PageList对象返回
            List<T> rows = baseMapper.queryData(queryObj);
            return new PageList<>(rows,total);
        }
        // 如果total=0,说明当前没有数据,问题是rows=null还是=[],如果total=0,rows返回一个空集合,不能是null,因为如果返回null给前端,前端很容易报错
        return new PageList<>();
    }

    @Transactional
    @Override
    public void batchRemove(List<Long> ids) {
        baseMapper.batchDelete(ids);
    }
}