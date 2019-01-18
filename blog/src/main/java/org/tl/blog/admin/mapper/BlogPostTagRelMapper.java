package org.tl.blog.admin.mapper;

import org.apache.ibatis.annotations.Param;
import org.tl.blog.admin.entity.BlogPostTagRel;
import org.tl.blog.common.base.BaseDao;

/**
*  @author TerryLam
*/
public interface BlogPostTagRelMapper extends BaseDao<BlogPostTagRel> {

    public int deleteByPostId(@Param("postId") Integer postId);

}