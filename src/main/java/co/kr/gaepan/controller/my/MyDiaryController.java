package co.kr.gaepan.controller.my;

import co.kr.gaepan.dto.pet.PetRegisterDTO;
import co.kr.gaepan.service.my.MyDiaryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;

@Controller
@RequestMapping("/my/*")
@Log4j2
public class MyDiaryController {

    @Autowired
    private MyDiaryService myDiaryService;

    @GetMapping("/diary")
    public String view(Model model, String pg, Authentication authentication) {

        // 사용자 로그인 정보에서 UID 가져오기
        String currentUserUid = null;
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                currentUserUid = ((UserDetails) principal).getUsername();
            }
        }


        // 페이징 처리
        int currentPage = myDiaryService.getCurrentPage(pg);
        log.info("currentPage : " + currentPage);
        int start = myDiaryService.getStartNum(currentPage);
        log.info("start : " + start);
        int total = myDiaryService.selectDiaryCountTotal(currentUserUid, 3);
        log.info("total : " + total);
        int lastPageNum = myDiaryService.getLastPageNum(total);
        log.info("lastPageNum : " + lastPageNum);
        int[] result = myDiaryService.getPageGroupNum(currentPage, lastPageNum);
        log.info("result : " + Arrays.toString(result));
        int pageStartNum = myDiaryService.getPageStartNum(total, lastPageNum);
        log.info("pageStartNum : " + pageStartNum);

        List<PetRegisterDTO> diaryList = myDiaryService.selectAll(currentUserUid, 3, start);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("total", total);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageGroupStart", result[0]);
        model.addAttribute("pageGroupEnd", result[1]);

        model.addAttribute("diaryList", diaryList);

        return "my/diary";
    }
}
