package co.kr.gaepan.controller.my;

import co.kr.gaepan.dto.board.BoardCateDTO;
import co.kr.gaepan.dto.board.BoardTypeDTO;
import co.kr.gaepan.dto.my.MyInfoDTO;
import co.kr.gaepan.dto.my.MyQnaDTO;
import co.kr.gaepan.mapper.my.MyQnaMapper;
import co.kr.gaepan.service.my.MyQnaService;
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

@Controller
@RequestMapping("/my/*")
@Log4j2
public class MyQnaController {

    @Autowired
    private MyQnaService myQnaService;

    @GetMapping("/qna")
    public String list(Model model, String pg, Authentication authentication, BoardCateDTO boardCateDTO, BoardTypeDTO boardTypeDTO) {

        // 사용자 로그인 정보에서 UID 가져오기
        String currentUserUid = null;
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                currentUserUid = ((UserDetails) principal).getUsername();
            }
        }

        // 페이징 처리
        int currentPage = myQnaService.getCurrentPage(pg);
        log.info("currentPage : " + currentPage);
        int start = myQnaService.getStartNum(currentPage);
        log.info("start : " + start);
        int total = myQnaService.selectQnaCountTotal(currentUserUid);
        log.info("total : " + total);
        int lastPageNum = myQnaService.getLastPageNum(total);
        log.info("lastPageNum : " + lastPageNum);
        int[] result = myQnaService.getPageGroupNum(currentPage, lastPageNum);
        log.info("result : " + Arrays.toString(result));
        int pageStartNum = myQnaService.getPageStartNum(currentPage, lastPageNum);
        log.info("pageStartNum : " + pageStartNum);

        // 문의사항 출력
        List<MyQnaDTO> qnaList = myQnaService.select_all(currentUserUid, start);

        // 1차 카테고리 출력
        List<BoardCateDTO> findCname = myQnaService.findCname(boardCateDTO);

        // 2차 카테고리 출력
        List<BoardTypeDTO> findTname = myQnaService.findTname(boardTypeDTO);

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("total", total);
        model.addAttribute("lastPageNum", lastPageNum);
        model.addAttribute("pageGroupStart", result[0]);
        model.addAttribute("pageGroupEnd", result[1]);

        model.addAttribute("qnaList", qnaList);
        model.addAttribute("findCname", findCname);
        model.addAttribute("findTname", findTname);

        return "my/qna";
    }
}
