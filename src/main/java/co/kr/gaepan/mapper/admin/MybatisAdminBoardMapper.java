package co.kr.gaepan.mapper.admin;

import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;
import co.kr.gaepan.dto.board.BoardCateDTO;
import co.kr.gaepan.dto.board.BoardDTO;
import co.kr.gaepan.dto.board.BoardTypeDTO;
import co.kr.gaepan.util.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MybatisAdminBoardMapper {

    // 조회수 증가
    void updateViewCnt(int bno) throws Exception;

    // 기본 조회 + 검색된 게시물 목록
    List<GP_AdminBoardDTO> searchList(SearchCriteria cri) throws Exception;

    // 게시물 상세 보기
    GP_AdminBoardDTO findById(int bno) throws Exception;

    // 게시글 수정
    void modifyById(GP_AdminBoardDTO dto) throws Exception;

    // 게시글 삭제(checkbox)
    void deleteAdminBoard(List<GP_AdminBoardDTO> boardDTO) throws Exception;

    // 게시글 삭제(bno)
    void deleteById(int bno) throws Exception;

    // 검색된 게시물의 전체 개수
    int searchListCount(SearchCriteria cri) throws Exception;

    // group에 따라 카테고리를 cateName에 맞게 출력
    List<BoardCateDTO> getCateName(String group) throws Exception;

    // cate에 따라 typeName 출력
    List<BoardTypeDTO> selectType(int cate) throws Exception;

    // cate에 따라 board List 출력
    List<GP_AdminBoardDTO> getCate(String group, int cate) throws Exception;

    List<BoardDTO> findByGroup(String group) throws Exception;

//    List<GP_AdminBoardDTO> cateNameAndTypeName(String group, int cate, int type) throws Exception;
}
