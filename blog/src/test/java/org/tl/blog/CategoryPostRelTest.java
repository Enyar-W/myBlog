package org.tl.blog;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tl.blog.admin.entity.BlogCategoryPostRel;
import org.tl.blog.admin.service.CategoryPostRelService;

public class CategoryPostRelTest extends BlogApplicationTests {

    @Autowired
    CategoryPostRelService categoryPostRelService;

    @Test
    public void testInsertCate() {
        BlogCategoryPostRel blogCategoryPostRel = new BlogCategoryPostRel();
        blogCategoryPostRel.setPostId(1);
        blogCategoryPostRel.setCateId(1);
        categoryPostRelService.insert(blogCategoryPostRel);
    }
}
