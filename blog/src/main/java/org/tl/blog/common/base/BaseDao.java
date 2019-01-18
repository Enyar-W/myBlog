package org.tl.blog.common.base;



import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.annotation.RegisterMapper;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

@RegisterMapper
@Repository
public interface BaseDao<T> extends Mapper<T>, MySqlMapper<T> {
}
