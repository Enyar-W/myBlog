package org.tl.blog.admin.service;

import org.tl.blog.admin.entity.BlogPost;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.common.base.BaseService;

import java.util.List;

public interface BlogPostService extends BaseService<BlogPost> {

    public void savePost(BlogPost blogPost, List<Integer> cateIds, List<Integer> tagIds);

    public void updatePost(BlogPost blogPost, List<Integer> cateIds, List<Integer> tagIds);
}
