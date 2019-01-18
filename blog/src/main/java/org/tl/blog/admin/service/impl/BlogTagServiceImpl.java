package org.tl.blog.admin.service.impl;


import org.springframework.stereotype.Service;
import org.tl.blog.admin.entity.BlogTag;
import org.tl.blog.admin.mapper.BlogTagMapper;
import org.tl.blog.admin.service.BlogTagService;
import org.tl.blog.common.base.BaseServiceImpl;

@Service
public class BlogTagServiceImpl extends BaseServiceImpl<BlogTagMapper, BlogTag> implements BlogTagService {
}
