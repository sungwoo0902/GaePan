package co.kr.gaepan.mapper.my;

import co.kr.gaepan.dto.board.BoardCateDTO;
import co.kr.gaepan.dto.board.BoardTypeDTO;
import co.kr.gaepan.dto.my.MyQnaDTO;
import co.kr.gaepan.dto.pet.PetAdoptApplyDTO;
import co.kr.gaepan.dto.pet.PetRegisterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyIndexMapper {
    List<MyQnaDTO> selectQna(String currentUserUid);

    List<PetRegisterDTO> selectDiary(String currentUserUid);

    List<PetAdoptApplyDTO> selectApplyList(String currentUserUid);
}
