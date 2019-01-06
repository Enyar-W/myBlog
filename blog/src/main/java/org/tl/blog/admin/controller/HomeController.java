package org.tl.blog.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class HomeController {

    @RequestMapping("/indexOk")
    @ResponseBody
    public String indexOk(){
        return "ok.";
    }


    @GetMapping({ "/index" })
    String index(Model model) {
        model.addAttribute("name", "Terry");
        model.addAttribute("username", "Terry");
        model.addAttribute("picUrl", "/img/photo_s.jpg");
        return "index";
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }
}
