package org.tl.blog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class HomeController {

    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "ok.";
    }
}
