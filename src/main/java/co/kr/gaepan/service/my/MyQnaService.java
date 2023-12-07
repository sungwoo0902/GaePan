package co.kr.gaepan.service.my;

import co.kr.gaepan.dto.board.BoardCateDTO;
import co.kr.gaepan.dto.board.BoardTypeDTO;
import co.kr.gaepan.dto.my.MyQnaDTO;
import co.kr.gaepan.mapper.my.MyQnaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
public class MyQnaService {

    private final MyQnaMapper myQnaMapper;

    // 문의글 10개씩 출력
    public List<MyQnaDTO> select_all(String uid, int start) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("uid", uid);
        parameters.put("start", start);
        return myQnaMapper.select_all(parameters);
    }


    // 페이징 시작
    public int selectQnaCountTotal(String uid) {
        return myQnaMapper.selectQnaCountTotal(uid);
    }

    public int getLastPageNum(int total) {

        int lastPageNum = 0;

        if(total % 10 == 0){
            lastPageNum = total / 10;
        }else{
            lastPageNum = total / 10 + 1;
        }

        return lastPageNum;
    }

    // 페이지 그룹
    public int[] getPageGroupNum(int currentPage, int lastPageNum) {
        int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
        int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
        int pageGroupEnd = currentPageGroup * 10;

        if(pageGroupEnd > lastPageNum){
            pageGroupEnd = lastPageNum;
        }

        int[] result = {pageGroupStart, pageGroupEnd};

        return result;
    }

    // 페이지 시작번호
    public int getPageStartNum(int total, int currentPage) {
        int start = (currentPage - 1) * 10;
        return total - start;
    }

    // 현재 페이지 번호
    public int getCurrentPage(String pg) {
        int currentPage = 1;

        if(pg != null){
            currentPage = Integer.parseInt(pg);
        }

        return currentPage;
    }

    // Limit 시작번호
    public int getStartNum(int currentPage) {
        return (currentPage - 1) * 10;
    }

    public List<BoardCateDTO> findCname(BoardCateDTO boardCateDTO) {
        return myQnaMapper.findCname(boardCateDTO);
    }

    public List<BoardTypeDTO> findTname(BoardTypeDTO boardTypeDTO) {
        return myQnaMapper.findTname(boardTypeDTO);
    }
}
