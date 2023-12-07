package co.kr.gaepan.service.my;

import co.kr.gaepan.dto.pet.PetRegisterDTO;
import co.kr.gaepan.mapper.my.MyDiaryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

@Service
@RequiredArgsConstructor
@Log4j2
public class MyDiaryService {

    private final MyDiaryMapper myDiaryMapper;

    public List<PetRegisterDTO> selectAll(String uid, Integer division, int start) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("uid", uid);
        parameters.put("division", division);
        parameters.put("start", start);

        return myDiaryMapper.selectAll(parameters);
    }

    public int selectDiaryCountTotal(String uid, Integer division) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("uid", uid);
        parameters.put("division", division);

        log.info("parameters : " + parameters);

        return myDiaryMapper.selectDiaryCountTotal(parameters);
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
}
