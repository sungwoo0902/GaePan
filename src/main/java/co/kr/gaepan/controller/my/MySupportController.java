package co.kr.gaepan.controller.my;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my/*")
public class MySupportController {

    @GetMapping("/support")
    public String view() {
        return ("my/support");
    }
}
