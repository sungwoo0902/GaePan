package co.kr.gaepan.controller.admin.board;


import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;
import co.kr.gaepan.dto.board.BoardCateDTO;
import co.kr.gaepan.dto.board.BoardTypeDTO;
import co.kr.gaepan.service.admin.AdminBoardCateService;
import co.kr.gaepan.service.admin.AdminBoardCommentService;
import co.kr.gaepan.service.admin.AdminBoardService;
import co.kr.gaepan.util.GP_Util;
import co.kr.gaepan.util.SearchCriteria;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.koreate.common.utils.PageMaker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/board/*")
@RequiredArgsConstructor
@Log4j2
public class AdminBoardController {

    private final AdminBoardService adminBoardService;
    private final AdminBoardCateService adminBoardCateService;
    private final AdminBoardCommentService adminBoardCommentService;
    private final GP_Util gpUtil;

    @GetMapping("/write")
    public String write(@RequestParam("group") String group,
                        @RequestParam("cate") int cate,
                        Model model) {
        try {
            List<BoardCateDTO> cateDTO = adminBoardCateService.getCateName(group);
            List<BoardTypeDTO> typeDTO = adminBoardCateService.selectType(cateDTO.get(0).getCate());

            log.info("cateDTO: " + cateDTO);
            log.info("typeDTO: " + typeDTO);

            model.addAttribute("cateDTO", cateDTO);
            model.addAttribute("typeDTO", typeDTO);
            model.addAttribute("group", group);
            model.addAttribute("cate", cate);

            // 로그인 된 nick 가져옴
            model.addAttribute("currentNick", gpUtil.getCurrentNick());
            log.info("write currentNick: " + gpUtil.getCurrentNick());
            model.addAttribute("currentRole", gpUtil.getCurrentRole());
            log.info("write currentRole: " + gpUtil.getCurrentRole());
        } catch (Exception e) {
            log.error("getWriteController error" + e.getMessage());
            throw new RuntimeException(e);
        }
        return "admin/board/write";
    }

    @PostMapping("/write")
    public String write(GP_AdminBoardDTO dto, HttpServletRequest request,
                    @RequestParam("group") String group,
                    @RequestParam("cate") int cate){
        try {
            String ip = request.getRemoteAddr();
            dto.setRegIP(ip);
            adminBoardService.saveAdminBoard(dto);
        } catch (Exception e) {
            log.error("getWriteController error" + e.getMessage());
            throw new RuntimeException(e);
        }

        return "redirect:list?group="+group + "&cate="+ cate;
    }

    @GetMapping("/list")
    public String pagingBoardList(@RequestParam(name = "group") String group,
                                  @RequestParam("cate") int cate,
                                  Model model, SearchCriteria cri)  {
        // board List 출력
        cri.setGroup(group);
        cri.setCate(cate);
        log.info("list group: " + group);
        log.info("list cate: " + cate);
        log.info("search criteria: " + cri);
        try {
            List<GP_AdminBoardDTO> adminBoardList
                    = adminBoardService.pagingBoardList(cri);
            model.addAttribute("adminBoardList", adminBoardList);

            List<BoardCateDTO> cateDTO = adminBoardCateService.getCateName(group);
            model.addAttribute("cateDTO", cateDTO);

            for (GP_AdminBoardDTO board : adminBoardList) {
                // 각 게시글에 대한 댓글 수를 조회하여 DTO에 설정
                int commentCount = adminBoardCommentService.countComments(board.getBno());
                board.setCommentCount(commentCount >= 0 ? commentCount : 0);
            }

            model.addAttribute("currentUid", gpUtil.getCurrentUsername());
            model.addAttribute("currentNick", gpUtil.getCurrentNick());
            model.addAttribute("currentRole", gpUtil.getCurrentRole());
        } catch (Exception e) {
            log.error("admin board list error", e.getMessage());
            throw new RuntimeException(e);
        }

        // 페이징 처리
        try {
            PageMaker pm = adminBoardService.getPageMaker(cri);
            model.addAttribute("pm", pm);
        } catch (Exception e) {
            log.error("admin board pm error", e.getMessage());
            throw new RuntimeException(e);
        }
        model.addAttribute("group", group);
        model.addAttribute("cate", cate);

        return "admin/board/list";
    }

    @GetMapping("/view")
    public String findById(HttpServletRequest request,
                           HttpServletResponse response,
                           Model model, GP_AdminBoardDTO dto,
                           @RequestParam("cate") int cate) {
        try {
            adminBoardService.updateViewCnt(request, response, dto.getBno());

            GP_AdminBoardDTO adminBoardDTO = adminBoardService.findById(dto.getBno());
            model.addAttribute("boardDTO", adminBoardDTO);

            List<GP_AdminBoardDTO> comments = adminBoardCommentService.findComments(dto);
            model.addAttribute("comments", comments);

            // 로그인 한 사람의 정보와 게시글 작성자와 비교
//            model.addAttribute("isboardCheckNick", gpUtil.checkAuthorization(adminBoardDTO.getNick()));
//            log.info("adminBoardDTO.getNick() : " + adminBoardDTO.getNick());

            model.addAttribute("currentUid", gpUtil.getCurrentUsername());
            model.addAttribute("currentNick", gpUtil.getCurrentNick());
            model.addAttribute("currentRole", gpUtil.getCurrentRole());

            // 댓글 비교
            for(GP_AdminBoardDTO comment : comments){
                model.addAttribute("iscommentCheckNick", gpUtil.checkAuthorization(comment.getNick()));
                log.info("comment.getNick() : " + comment.getNick());
            }

//            log.info("admin board findById comments : " + comments);
        } catch (Exception e) {
            log.error("admin board view error", e.getMessage());
            throw new RuntimeException(e);
        }
        model.addAttribute("cate", cate);

        return "admin/board/view";
    }

    @GetMapping("/modify")
    public String modify(@RequestParam("group") String group,
//                         @RequestParam("cate") int cate,
                         Model model, int bno){

        try {
            List<BoardCateDTO> cateDTO = adminBoardCateService.getCateName(group);
            List<BoardTypeDTO> typeDTO = adminBoardCateService.selectType(cateDTO.get(0).getCate());
            GP_AdminBoardDTO boardDTO = adminBoardService.findById(bno);

            log.info("modify cate : " + cateDTO.get(0).getCate());
         /*   log.info("cateDTO: " + cateDTO);
            log.info("typeDTO: " + typeDTO);*/

            model.addAttribute("cateDTO", cateDTO);
            model.addAttribute("typeDTO", typeDTO);
            model.addAttribute("boardDTO", boardDTO);
//            model.addAttribute("cate", cate);


            model.addAttribute("currentNick", gpUtil.getCurrentNick());
            model.addAttribute("currentRole", gpUtil.getCurrentRole());
        } catch (Exception e) {
            log.error("getModifyController error" + e.getMessage());
            throw new RuntimeException(e);
        }
        return "admin/board/modify";
    }

    @PostMapping("/modify")
    public String modify(GP_AdminBoardDTO dto,
                         @RequestParam("bno") int bno,
                         @RequestParam("cate") int cate){
        log.info("modify post bno:" + bno);
        log.info("modify post cate:" + cate);
        try {
            adminBoardService.modifyAdminBoard(dto);
        } catch (Exception e) {
            log.error("PostModifyController error" + e.getMessage());
            throw new RuntimeException(e);
        }
        return "redirect:view?bno=" + bno+ "&cate=" +cate;
    }


    @PostMapping("/commentWrite")
    public String commentWrite(GP_AdminBoardDTO dto, HttpServletRequest request,
                            @RequestParam("bno") int parent,
                            @RequestParam("cate") int cate){
        log.info("처음 parent : " + parent);
        log.info("처음 cate : " + cate);
        try {
            String ip = request.getRemoteAddr();
            dto.setRegIP(ip);

            adminBoardCommentService.saveComment(dto);
            log.info("dto cate : "+ dto.getCate());
/*            log.info("dto group:" + dto.getGroup());
            log.info("dto type:" + dto.getType());
            log.info("dto comment : "+ dto.getComment());
            log.info("dto parent:" + dto.getParent());*/

        } catch (Exception e) {
            log.error("getWriteController error" + e.getMessage());
            throw new RuntimeException(e);
        }
        return "redirect:view?bno=" + parent + "&cate=" +cate;
    }
}
