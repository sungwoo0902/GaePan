package co.kr.gaepan.service.admin;

import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;

import java.util.List;

public interface AdminBoardCommentService {

    // 댓글 리스트
    List<GP_AdminBoardDTO> findComments(GP_AdminBoardDTO dto) throws Exception;
    
    // 댓글 작성
    void saveComment(GP_AdminBoardDTO dto) throws Exception;

    // 댓글 수정
    void modifyComment(int bno, String comment) throws Exception;

    // 댓글 삭제
    void deleteComment(int bno) throws Exception;

    // 댓글 개수
    int countComments(int bno) throws Exception;


}
