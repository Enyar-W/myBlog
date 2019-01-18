package org.tl.blog.admin.service.impl;

import org.springframework.stereotype.Service;
import org.tl.blog.admin.entity.BlogCategory;
import org.tl.blog.admin.mapper.BlogCategoryMapper;
import org.tl.blog.admin.service.BlogCategoryService;
import org.tl.blog.common.base.BaseServiceImpl;
import org.tl.blog.common.utils.Pager;
import java.util.List;

@Service
public class BlogCategoryServiceImpl extends BaseServiceImpl<BlogCategoryMapper, BlogCategory> implements BlogCategoryService {

    @Override
    public Pager<BlogCategory> findCategroyList(Integer start, Integer pageSize, BlogCategory blogCategory) {
        return super.pager(start,pageSize,blogCategory);
    }
}
