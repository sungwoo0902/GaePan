package co.kr.gaepan.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/*")
@Controller
public class AdminMainController {

    @GetMapping("/index")
    public String index() {
        return "admin/index";
    }

}
