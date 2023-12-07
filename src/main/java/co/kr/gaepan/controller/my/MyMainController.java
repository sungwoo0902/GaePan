package co.kr.gaepan.controller.my;

import co.kr.gaepan.dto.board.BoardCateDTO;
import co.kr.gaepan.dto.board.BoardTypeDTO;
import co.kr.gaepan.dto.my.MyQnaDTO;
import co.kr.gaepan.dto.pet.PetAdoptApplyDTO;
import co.kr.gaepan.dto.pet.PetRegisterDTO;
import co.kr.gaepan.service.my.MyIndexService;
import co.kr.gaepan.service.my.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/my/*")
@Controller
public class MyMainController {

    private MyIndexService myIndexService;

    @Autowired
    public MyMainController(MyIndexService myIndexService) {
        this.myIndexService = myIndexService;
    }

    @GetMapping("/index")
    public String index(Model model, Authentication authentication, BoardCateDTO boardCateDTO, BoardTypeDTO boardTypeDTO){

        // 사용자 로그인 정보에서 UID 가져오기
        String currentUserUid = null;
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                currentUserUid = ((UserDetails) principal).getUsername();
            }
        }

        // 문의사항 출력
        List<MyQnaDTO> qnaList = myIndexService.selectQna(currentUserUid);

        // 입양일키 출력
        List<PetRegisterDTO> diaryList = myIndexService.selectDiary(currentUserUid);

        // 최근분양신청내역 출력
        List<PetAdoptApplyDTO> selectApplyList = myIndexService.selectApplyList(currentUserUid);

        model.addAttribute("qnaList", qnaList);
        model.addAttribute("diaryList", diaryList);
        model.addAttribute("selectApplyList", selectApplyList);

        return "my/index";
    }
}
