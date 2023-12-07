package co.kr.gaepan.mapper.my;

import co.kr.gaepan.dto.board.BoardCateDTO;
import co.kr.gaepan.dto.board.BoardTypeDTO;
import co.kr.gaepan.dto.my.MyQnaDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyQnaMapper {

    // 페이징 - 전체 게시글 갯수 계산
    public int selectQnaCountTotal(String uid);

    public List<BoardCateDTO> findCname(BoardCateDTO boardCateDTO);

    public List<BoardTypeDTO> findTname(BoardTypeDTO boardTypeDTO);

    // 나의 리뷰 10개씩 출력
    List<MyQnaDTO> select_all(Map<String, Object> parameters);
}
