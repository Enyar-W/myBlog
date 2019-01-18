package org.tl.blog.admin.service.impl;

import org.springframework.stereotype.Service;
import org.tl.blog.admin.entity.BlogCategoryPostRel;
import org.tl.blog.admin.entity.BlogPost;
import org.tl.blog.admin.mapper.BlogCategoryPostRelMapper;
import org.tl.blog.admin.mapper.BlogPostMapper;
import org.tl.blog.admin.service.CategoryPostRelService;
import org.tl.blog.common.base.BaseServiceImpl;

@Service
public class CategoryPostRelServiceImpl extends BaseServiceImpl<BlogCategoryPostRelMapper, BlogCategoryPostRel> implements CategoryPostRelService {
}
