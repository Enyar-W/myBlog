package org.tl.blog.admin.service.impl;


import org.springframework.stereotype.Service;
import org.tl.blog.admin.entity.BlogMenu;
import org.tl.blog.admin.mapper.BlogMenuMapper;
import org.tl.blog.admin.service.BlogMenuService;
import org.tl.blog.common.base.BaseServiceImpl;

@Service
public class BlogMenuServiceImpl extends BaseServiceImpl<BlogMenuMapper, BlogMenu> implements BlogMenuService {
}
