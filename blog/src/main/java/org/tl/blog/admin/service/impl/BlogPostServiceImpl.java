package org.tl.blog.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tl.blog.admin.entity.BlogPost;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.admin.mapper.BlogPostMapper;
import org.tl.blog.admin.mapper.SysUserMapper;
import org.tl.blog.admin.service.BlogPostService;
import org.tl.blog.admin.service.SysUserService;
import org.tl.blog.common.base.BaseServiceImpl;
import org.tl.blog.common.upload.UploadService;

@Service
public class BlogPostServiceImpl extends BaseServiceImpl<BlogPostMapper, BlogPost> implements BlogPostService {

    @Autowired
    private UploadService uploadService;

    public void savePost(BlogPost blogPost){
        String contentHtml = uploadService.handlerOuterImage(blogPost.getPostContent());
        blogPost.setPostContent(contentHtml);
        insert(blogPost);
    }

    public void updatePost(BlogPost blogPost){
        String contentHtml = uploadService.handlerOuterImage(blogPost.getPostContent());
        blogPost.setPostContent(contentHtml);
        update(blogPost);
    }

}
