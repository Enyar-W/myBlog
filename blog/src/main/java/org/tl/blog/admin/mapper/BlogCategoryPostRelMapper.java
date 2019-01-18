package org.tl.blog.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.tl.blog.admin.entity.BlogCategoryPostRel;
import org.tl.blog.common.base.BaseDao;

import java.util.Map;

/**
*  @author TerryLam
*/

public interface BlogCategoryPostRelMapper extends BaseDao<BlogCategoryPostRel> {


    public int deleteNotInCateId(Map<String,Object> map);

    public int deleteByPostId(@Param("postId") Integer postId);
}