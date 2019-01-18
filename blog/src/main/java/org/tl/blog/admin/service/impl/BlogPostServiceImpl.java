package org.tl.blog.admin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tl.blog.admin.entity.BlogCategoryPostRel;
import org.tl.blog.admin.entity.BlogPost;
import org.tl.blog.admin.entity.BlogPostTagRel;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.admin.mapper.BlogCategoryPostRelMapper;
import org.tl.blog.admin.mapper.BlogPostMapper;
import org.tl.blog.admin.mapper.BlogPostTagRelMapper;
import org.tl.blog.admin.mapper.SysUserMapper;
import org.tl.blog.admin.service.BlogPostService;
import org.tl.blog.admin.service.SysUserService;
import org.tl.blog.common.base.BaseServiceImpl;
import org.tl.blog.common.upload.UploadService;
import org.tl.blog.common.utils.DateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlogPostServiceImpl extends BaseServiceImpl<BlogPostMapper, BlogPost> implements BlogPostService {

    @Autowired
    private UploadService uploadService;

    @Autowired
    private BlogCategoryPostRelMapper categoryPostRelMapper;

    @Autowired
    private BlogPostTagRelMapper postTagRelMapper;

    /**
     * 保存文章
     * @param blogPost 文章实体
     * @param cateIds 类别ids
     * @param tagIds 标志ids
     */
    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void savePost(BlogPost blogPost, List<Integer> cateIds, List<Integer> tagIds){
        String contentHtml = uploadService.handlerOuterImage(blogPost.getPostContent());
        blogPost.setPostContent(contentHtml);
        insert(blogPost);
        batchInsertRel(blogPost,cateIds,tagIds);

    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void updatePost(BlogPost blogPost, List<Integer> cateIds, List<Integer> tagIds){
        String contentHtml = uploadService.handlerOuterImage(blogPost.getPostContent());
        blogPost.setPostContent(contentHtml);
        update(blogPost);
        /*Map<String,Object> map = new HashMap<>();
        map.put("postId",blogPost.getPostId());
        map.put("cateId",cateIds);
        categoryPostRelMapper.deleteNotInCateId(map);*/
        categoryPostRelMapper.deleteByPostId(blogPost.getPostId());
        postTagRelMapper.deleteByPostId(blogPost.getPostId());
        batchInsertRel(blogPost,cateIds,tagIds);
    }

    private void batchInsertRel(BlogPost blogPost, List<Integer> cateIds, List<Integer> tagIds){
        List<BlogCategoryPostRel> blogCategoryPostRels = new ArrayList<BlogCategoryPostRel>();
        cateIds.stream().forEach(cateId->{
            BlogCategoryPostRel blogCategoryPostRel = new BlogCategoryPostRel();
            blogCategoryPostRel.setPostId(blogPost.getPostId());
            blogCategoryPostRel.setCateId(cateId);
            blogCategoryPostRels.add(blogCategoryPostRel);
        });
        categoryPostRelMapper.insertList(blogCategoryPostRels);

        List<BlogPostTagRel> blogPostTagRels = new ArrayList<BlogPostTagRel>();
        tagIds.stream().forEach(tagId->{
            BlogPostTagRel blogPostTagRel = new BlogPostTagRel();
            blogPostTagRel.setPostId(blogPost.getPostId());
            blogPostTagRel.setTagId(tagId);
            blogPostTagRels.add(blogPostTagRel);
        });
        postTagRelMapper.insertList(blogPostTagRels);
    }
}
