package org.tl.blog.common.base;

import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class BaseServiceImpl<T> implements BaseService<T>{



    @Override
    public int insert(T entity) {
        return 0;
    }

    @Override
    public int delete(Serializable id) {
        return 0;
    }

    @Override
    public int update(T entity) {
        return 0;
    }

    @Override
    public T findById(Serializable id) {
        return null;
    }
}
