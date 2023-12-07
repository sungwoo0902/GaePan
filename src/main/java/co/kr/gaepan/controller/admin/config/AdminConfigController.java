package co.kr.gaepan.controller.admin.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/config/*")
@Controller
public class AdminConfigController {
    @GetMapping("/info")
    public String config() {
        return "admin/config/info";
    }

    @GetMapping("/banner")
    public String banner() {
        return "admin/config/banner";
    }
}
