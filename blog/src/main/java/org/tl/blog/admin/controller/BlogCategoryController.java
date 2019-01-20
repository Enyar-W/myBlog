package org.tl.blog.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.tl.blog.admin.entity.BlogCategory;
import org.tl.blog.admin.service.BlogCategoryService;
import org.tl.blog.common.base.BaseController;
import org.tl.blog.common.utils.AjaxResponse;
import org.tl.blog.common.utils.Pager;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin/category")
public class BlogCategoryController extends BaseController {

    @Autowired
    private BlogCategoryService blogCategoryService;

    @RequestMapping({"/",""})
    public String show(){
        return "/admin/category/category";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Pager<BlogCategory> list(HttpServletRequest request){
        Integer pageNo = getParamInt("pageNo", 1);
        Integer pageSize = getParamInt("pageSize", 10);
        BlogCategory blogCategory = new BlogCategory();
        Pager<BlogCategory> categroyList = blogCategoryService.findCategroyList(pageNo, pageSize, blogCategory);
        return categroyList;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResponse doUpdate(BlogCategory blogCategory){
        AjaxResponse ajax = new AjaxResponse();
        if (blogCategory.getCateId()==null){
            throw new RuntimeException("id不能空！");
        }
        try {
            blogCategoryService.updateNotNull(blogCategory);
        }catch (Exception e){
            ajax.setMsg("服务器发生错误");
        }
        return ajax;
    }

    @RequestMapping(value = "/find",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResponse find(BlogCategory blogCategory){
        AjaxResponse ajax = new AjaxResponse();
        if(blogCategory.getCateId() == null){
            throw new RuntimeException("id不能空！");
        }
        BlogCategory blogCategoryDO = blogCategoryService.findById(blogCategory.getCateId());
        if(blogCategoryDO == null){
            throw new RuntimeException("对象为空");
        }
        ajax.setSuccess(true);
        ajax.setMsg("get object success");
        ajax.setObj(blogCategoryDO);
        return ajax;
    }
}
