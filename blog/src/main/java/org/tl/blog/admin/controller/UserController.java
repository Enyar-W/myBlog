package org.tl.blog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.common.base.BaseController;

@Controller
@RequestMapping("/admin")
public class UserController extends BaseController {

    public String login(SysUser user){
        return "";
    }

}
