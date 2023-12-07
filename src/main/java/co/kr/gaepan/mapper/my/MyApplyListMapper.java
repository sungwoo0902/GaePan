package co.kr.gaepan.mapper.my;

import co.kr.gaepan.dto.pet.PetAdoptApplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyApplyListMapper {

    List<PetAdoptApplyDTO> applyAll(Map<String, Object> parameters);

    int selectApplyListCountTotal(String uid);
}
