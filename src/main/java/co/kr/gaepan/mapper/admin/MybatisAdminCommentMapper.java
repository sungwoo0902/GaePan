package co.kr.gaepan.mapper.admin;

import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MybatisAdminCommentMapper {

    // 댓글 조회
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
