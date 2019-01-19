package org.tl.blog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.tl.blog.common.base.BaseController;

@Controller
@RequestMapping("/admin/category")
public class BlogCategoryController extends BaseController {

    @RequestMapping("/list")
    public String list(){
        return "/admin/category/category";
    }
}
