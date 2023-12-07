package co.kr.gaepan.service.admin;


import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;
import co.kr.gaepan.dto.board.BoardCateDTO;
import co.kr.gaepan.dto.board.BoardDTO;
import co.kr.gaepan.dto.board.BoardTypeDTO;
import co.kr.gaepan.util.SearchCriteria;

import java.util.List;

public interface AdminBoardCateService {

    // board의 group과 cateDTO를 join해서 cateName가져오기
    List<BoardCateDTO> getCateName(String group) throws Exception;

    // cate에 따라 type변경
    List<BoardTypeDTO> selectType(int cate) throws Exception;

}
