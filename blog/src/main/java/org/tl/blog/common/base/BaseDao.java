package org.tl.blog.common.base;



import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.BaseMapper;

@RegisterMapper
@Repository
public interface BaseDao<T> extends BaseMapper<T> {
}
