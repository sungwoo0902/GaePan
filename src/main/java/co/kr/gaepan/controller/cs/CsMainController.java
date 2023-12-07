package co.kr.gaepan.controller.cs;


import co.kr.gaepan.dto.board.BoardDTO;
import co.kr.gaepan.dto.cs.PageRequestDTO;
import co.kr.gaepan.dto.cs.PageResponseDTO;
import co.kr.gaepan.service.cs.CsBoardService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/cs/*")
@Controller
@Log4j2
public class CsMainController {

    @Autowired
    private CsBoardService csBoardService;

    @GetMapping("/index")
    public String index(){
        return "cs/index"; // return 시작할 때 / 붙이지 않기
    }
    @GetMapping("/notice/list")
    public String noticeList(Model model, PageRequestDTO pageRequestDTO){ // DTO 속성으로 group, cate 있으므로 파라미터 받을 수 있음

        // pg 값이 변경될 때마다 호출하여 offset 갱신
        pageRequestDTO.updateOffset();
        // ToString 이용해서 pageRequestDTO 속성 확인
        log.info("noticeList...1 : " + pageRequestDTO);

        PageResponseDTO pageResponseDTO = csBoardService.findByGroupAndCate(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);

        model.addAttribute(pageResponseDTO);

        return "cs/notice/list";
    }
    @GetMapping("/notice/view")
    public String noticeView(Model model, int bno) {

        // 글 조회
        BoardDTO boardDTO= csBoardService.findByNo(bno);
        log.info("boardDTO : " + boardDTO);

        model.addAttribute(boardDTO); // 속성이름 없이 바로 설정하는 건 이렇게 DTO만 가능한가봄

        return "cs/notice/view";
    }
    @GetMapping("/faq/list")
    public String faqList(Model model, PageRequestDTO pageRequestDTO){ // DTO 속성으로 group, cate 있으므로 파라미터 받을 수 있음

        // pg 값이 변경될 때마다 호출하여 offset 갱신
        pageRequestDTO.updateOffset();
        // ToString 이용해서 pageRequestDTO 속성 확인
        log.info("faqList...1 : " + pageRequestDTO);

        PageResponseDTO pageResponseDTO = csBoardService.findByGroupAndCate(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);

        model.addAttribute(pageResponseDTO);

        return "cs/faq/list";
    }
    @GetMapping("/qna/list")
    public String qnaList(Principal principal, Model model, PageRequestDTO pageRequestDTO){ // DTO 속성으로 group, cate 있으므로 파라미터 받을 수 있음

        if(principal == null){
            log.info("로그인 안됨");
        }else{
            log.info("로그인 됨 userName : " + principal.getName());
        }


        // pg 값이 변경될 때마다 호출하여 offset 갱신
        pageRequestDTO.updateOffset();
        // ToString 이용해서 pageRequestDTO 속성 확인
        log.info("qnaList...1 : " + pageRequestDTO);

        PageResponseDTO pageResponseDTO = csBoardService.findByGroupAndCate(pageRequestDTO);
        log.info("pageResponseDTO : " + pageResponseDTO);

        model.addAttribute(pageResponseDTO);

        if(principal != null){
            model.addAttribute("currentUser", principal.getName());
        }

        return "cs/qna/list";
    }
    // AJAX활용을 위한 PostMapping
    @ResponseBody
    @PostMapping("/qna/list")
    public String qnaList(int bno){
        log.info("qnaList : " + bno);
        BoardDTO boardDTO = csBoardService.findByNo(bno);
        String uid = boardDTO.getUid();
        return uid;
    }
    @GetMapping("/qna/view")
    public String qnaView(Model model, int bno) {

        // 현재 사용자의 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 변수 선언 및 초기화
        String uid = null;
        String role = null;

        if(principal instanceof UserDetails) {

            // uid는 현재 인증된 사용자의 아이디 / 이렇게 할 필요없이 qna/list처럼 prinipal 객체 바로 이용하면됨
            uid = ((UserDetails) principal).getUsername();
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails) principal).getAuthorities();

            GrantedAuthority authority = authorities.get(0);

            // role 값 substring  어떻게 할지 확인하기!
            //role = authority.getAuthority().substring(5);
        }

        model.addAttribute("uid", uid);

        if(uid == null){

            return "redirect:/member/login";
        }

        // 글 조회
        BoardDTO boardDTO= csBoardService.findByNo(bno);
        log.info("boardDTO : " + boardDTO);
        //답변 조회(리스트)
        List<BoardDTO> replies = csBoardService.findByParent(bno);
        log.info("replies : " + replies);


        model.addAttribute(boardDTO); // 속성이름 없이 바로 설정하는 건 이렇게 DTO만 가능한가봄
        model.addAttribute("replies", replies);

        return "cs/qna/view";
    }
    @PostMapping("/qna/view")
    public String qnaView(HttpServletRequest request, BoardDTO boardDTO){ // 폼전송하면 여기 BoardDTO로 바인딩된다

        // 현재 날짜 및 시간 가져오기
        LocalDateTime regDate = LocalDateTime.now();
        // boardDTO의 regDate 속성으로 설정
        boardDTO.setRegDate(regDate);

        // 현재 사용자 IP 주소 가져오기
        String regIP = request.getRemoteAddr();
        // boardDTO의 regIp 속성으로 설정
        boardDTO.setRegIP(regIP);

        log.info("boarDTO : " + boardDTO);
        csBoardService.insertComment(boardDTO);

        //return "cs/qna/view?bno=" + boardDTO.getBno(); 이렇게 cs/qna/view가 아니라 URL 변경하려면 redirect 처리해야함
        // list에서 view로 갈때 처럼 똑같이 파라미터 보내줘야함 / ★파라미터 중요! 폼에서 전송받은 parent 값으로 파라미터를 보내어 글 조회한다!!!
        return "redirect:/cs/qna/view?bno=" + boardDTO.getParent();
    }

    @GetMapping("/qna/write")
    public String qnaWrite(Model model){

        // 현재 사용자의 정보 가져오기
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // 변수 선언 및 초기화
        String uid = null;
        String role = null;

        if(principal instanceof UserDetails) {

            // uid는 현재 인증된 사용자의 아이디
            uid = ((UserDetails) principal).getUsername();
            List<GrantedAuthority> authorities = (List<GrantedAuthority>) ((UserDetails) principal).getAuthorities();

            GrantedAuthority authority = authorities.get(0);

            // role 값 substring  어떻게 할지 확인하기!
            role = authority.getAuthority().substring(5);
        }

        model.addAttribute("uid", uid);

        if(uid == null){

            return "redirect:/member/login";
        }

        return "cs/qna/write";
    }
    @PostMapping("/qna/write")
    public String qnaWrite(HttpServletRequest request, BoardDTO boardDTO){ // DTO 받을 때 regDate값은 자동으로 지정되는 건가??

        // 현재 날짜 및 시간 가져오기
        LocalDateTime regDate = LocalDateTime.now();
        // boardDTO의 regDate 속성으로 설정
        boardDTO.setRegDate(regDate);

        // 현재 사용자 IP 주소 가져오기
        String regIP = request.getRemoteAddr();
        // boardDTO의 regIp 속성으로 설정
        boardDTO.setRegIP(regIP);

        log.info("boardDTO : " + boardDTO);
        csBoardService.insertInquiry(boardDTO);

        // 가독성 위해 String.format 활용
        // return String.format("redirect:/cs/qna/list?group=%s&cate=%s", boardDTO.getGroup(), boardDTO.getCate());
        return "redirect:/cs/qna/list?group=qna&cate=31";  // 파라미터 안 보내면 오류나겠지??

    }

    // 댓글 삭제
    @GetMapping("/qna/commentDelete")
    public String qnaCommentDelete(BoardDTO boardDTO){ //BoardDTO로 bno랑 parent값 받자

        log.info("BoardDTO : " +boardDTO);
        csBoardService.deleteArticle(boardDTO.getBno());


        return "redirect:/cs/qna/view?bno=" + boardDTO.getParent();
    }
    // 글 삭제
    @GetMapping("/qna/articleDelete")
    public String qnaArticleDelete(BoardDTO boardDTO){

        // bno, cate값 확인하기
        log.info("BoardDTO : " +boardDTO);
        csBoardService.deleteArticle(boardDTO.getBno());

        return "redirect:/cs/qna/list?group=qna&cate=" + boardDTO.getCate();
    }

    // 댓글 수정
    @GetMapping("/qna/commentUpdate")
    public String qnaCommentUpdate(BoardDTO boardDTO){

        // comment, bno, parent값 확인하기
        log.info("BoardDTO : " +boardDTO);
        csBoardService.updateComment(boardDTO);

        return "redirect:/cs/qna/view?bno=" + boardDTO.getParent();
    }

    //글 수정
    @GetMapping("/qna/articleUpdate")
    public String qnaArticleUpdate(BoardDTO boardDTO){

        // bno, title, content 값 확인하기
        csBoardService.updateArticle(boardDTO);

        return "redirect:/cs/qna/view?bno=" + boardDTO.getBno();
    }

}

