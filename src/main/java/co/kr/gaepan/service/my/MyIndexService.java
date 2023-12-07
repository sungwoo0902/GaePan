package co.kr.gaepan.service.my;

import co.kr.gaepan.dto.board.BoardCateDTO;
import co.kr.gaepan.dto.board.BoardTypeDTO;
import co.kr.gaepan.dto.my.MyQnaDTO;
import co.kr.gaepan.dto.pet.PetAdoptApplyDTO;
import co.kr.gaepan.dto.pet.PetRegisterDTO;
import co.kr.gaepan.mapper.my.MyIndexMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MyIndexService {

    private final MyIndexMapper myIndexMapper;

    public List<MyQnaDTO> selectQna(String currentUserUid) {
        return myIndexMapper.selectQna(currentUserUid);
    }

    public List<PetRegisterDTO> selectDiary(String currentUserUid) {
        return myIndexMapper.selectDiary(currentUserUid);
    }

    public List<PetAdoptApplyDTO> selectApplyList(String currentUserUid) {
        return myIndexMapper.selectApplyList(currentUserUid);
    }
}
