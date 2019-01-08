package org.tl.blog.common.base;

import com.github.pagehelper.PageHelper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.tl.blog.common.utils.Pager;
import java.io.Serializable;

import java.util.List;

@Log4j2
public class BaseServiceImpl<M extends BaseDao<T>, T> implements BaseService<T>{

    @Autowired
    protected M baseMapper;

    @Override
    public int insert(T entity) {
        return baseMapper.insert(entity);
    }

    @Override
    public int delete(Serializable id) {
        return baseMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(T entity) {
        return baseMapper.updateByPrimaryKey(entity);
    }

    @Override
    public T findById(Serializable id) {
        //Weekend<T> weekend = new Weekend<T>( (Class < T > ) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[ 0 ]);
        //Example.Criteria criteria = weekend.createCriteria();
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public Pager<T> pager(Integer start, Integer pageSize,T t) {
        PageHelper.startPage(start,pageSize);
        List<T> list = baseMapper.select(t);
        int totalNum = baseMapper.selectCount(t);
        Pager<T> pager = new Pager<>(start,pageSize,totalNum);
        pager.setResult(list);
        return pager;
    }
}
