package org.tl.blog.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tl.blog.admin.mapper.BaseMapper;

import java.io.Serializable;

@Service
public class BaseServiceImpl<T> implements BaseService<T>{

    @Autowired
    private BaseMapper<T> baseMapper;

    @Override
    public int insert(T entity) {
        baseMapper.insert(entity);
        return 0;
    }

    @Override
    public int delete(Serializable id) {
        baseMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int update(T entity) {
        baseMapper.updateByPrimaryKey(entity);
        return 0;
    }

    @Override
    public T findById(Serializable id) {
        return baseMapper.selectByPrimaryKey(id);
    }
}
