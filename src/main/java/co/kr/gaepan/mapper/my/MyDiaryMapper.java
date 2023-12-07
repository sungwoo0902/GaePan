package co.kr.gaepan.mapper.my;

import co.kr.gaepan.dto.pet.PetRegisterDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.OptionalInt;

@Mapper
public interface MyDiaryMapper {

    int selectDiaryCountTotal(Map<String, Object> parameters);

    List<PetRegisterDTO> selectAll(Map<String, Object> parameters);


}
