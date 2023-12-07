package co.kr.gaepan.controller.my;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my")
public class MyRegular_SupportController {

    @GetMapping("/regular_support.html")
    public String view() {
        return "my/regular_support";
    }
}

