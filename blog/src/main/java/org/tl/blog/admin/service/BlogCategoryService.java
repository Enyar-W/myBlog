package org.tl.blog.admin.service;

import org.tl.blog.admin.entity.BlogCategory;
import org.tl.blog.admin.entity.BlogPost;
import org.tl.blog.common.base.BaseService;
import org.tl.blog.common.utils.Pager;

import java.util.List;

public interface BlogCategoryService extends BaseService<BlogCategory> {

    public Pager<BlogCategory> findCategroyList(Integer start, Integer pageSize, BlogCategory blogCategory);

}
