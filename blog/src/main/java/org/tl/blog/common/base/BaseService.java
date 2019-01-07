package org.tl.blog.common.base;

import org.tl.blog.common.utils.Pager;

import java.io.Serializable;

public interface BaseService<T> {

    public int insert(T entity);//增加

    public int delete(Serializable id);//删除

    public int update(T entity);//修改

    public T findById(Serializable id);//查询

    public Pager<T> pager(Integer start, Integer pageSize, T t);//分页查询

}
