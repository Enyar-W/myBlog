package org.tl.blog;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tl.blog.admin.entity.BlogPost;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.admin.service.BlogPostService;
import org.tl.blog.common.utils.Pager;

public class BlogPostTest extends BlogApplicationTests {

    @Autowired
    BlogPostService postService;

    @Test
    public void insert() {
        //SysUser sysUser = sysUserMapper.selectByPrimaryKey(1);
        BlogPost blogPost = new BlogPost();
       // blogPost.setPostId(1);
        blogPost.setPostContent("fffffffff");
        postService.insert(blogPost);

    }

    @Test
    public void pager() {
        BlogPost blogPost = new BlogPost();
        Pager<BlogPost> pager = postService.pager(2, 5, blogPost);
        System.out.println(pager.getTotalPage());
        for (BlogPost blogPost1: pager.getResult()) {
            System.out.println(blogPost1.getPostId());
        }

    }

}
