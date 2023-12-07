package co.kr.gaepan.controller.admin.rest;

import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;
import co.kr.gaepan.dto.board.BoardCateDTO;
import co.kr.gaepan.dto.board.BoardTypeDTO;
import co.kr.gaepan.service.admin.AdminBoardCateService;
import co.kr.gaepan.service.admin.AdminBoardCommentService;
import co.kr.gaepan.service.admin.AdminBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin/board/*")
public class RestAdminBoard {

    private final AdminBoardService adminBoardService;
    private final AdminBoardCateService adminBoardCateService;
    private final AdminBoardCommentService adminBoardCommentService;

    @PostMapping("/ajaxCate")
    public List<BoardCateDTO> selectCate(@RequestParam String group) {
        log.info("ajax group : " + group);
        try {
            return adminBoardCateService.getCateName(group);
        } catch (Exception e) {
            log.error("ajax cate error : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/ajaxType")
    public List<BoardTypeDTO> selectType(@RequestParam int cate){
        log.info("ajax cate : " + cate);
        try {
            return adminBoardCateService.selectType(cate);
        } catch (Exception e) {
            log.error("ajax select type error : " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    // 게시글 checkbox로 삭제
    @PostMapping("/deleteBoardList")
    public ResponseEntity<String> deleteBoardList(@RequestBody List<GP_AdminBoardDTO> boardDTO){
        log.info("deleteBoardList : " + boardDTO);
        try {
            adminBoardService.deleteAdminBoard(boardDTO);
            return ResponseEntity.ok("게시글이 삭제되었습니다.");
        } catch (Exception e) {
            log.error("ajax admin deleteBoardList error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 삭제에 실패했습니다.");
        }
    }

    // 게시글 bno값을 통해서 1개만 삭제
    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam int bno) {
        log.info("ajax bno : " + bno);
        try {
            adminBoardService.deleteById(bno);
            return ResponseEntity.ok("게시글이 삭제되었습니다.");
        } catch (Exception e) {
            log.error("ajax admin deleteById error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 삭제에 실패했습니다.");
        }
    }

    @PutMapping("/modifyComment")
    public ResponseEntity<String> modifyComment(@RequestParam int bno,
                                                @RequestParam String comment) {
        log.info("BNO : " + bno);
        log.info("COMMENT : " + comment);
        try {
            log.info("2");
            adminBoardCommentService.modifyComment(bno, comment);
            return ResponseEntity.ok("댓글 수정 성공");
        } catch (Exception e) {
            log.error("ajax admin modifyComment error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 수정 실패");
        }
    }
    
    @DeleteMapping("/deleteComment")
    public ResponseEntity<String> deleteComment(@RequestParam int bno){
        log.info("ajax deleteComment bno : " + bno);
        try {
            adminBoardCommentService.deleteComment(bno);
            return ResponseEntity.ok("댓글 삭제 성공");
        } catch (Exception e) {
            log.error("ajax admin deleteById error : " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("댓글 삭제 실패");
        }
    }
}
