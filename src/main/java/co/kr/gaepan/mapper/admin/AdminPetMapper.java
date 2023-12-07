package co.kr.gaepan.mapper.admin;

import co.kr.gaepan.dto.pet.PetRegisterDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminPetMapper {

    public List<PetRegisterDTO> AdminPet(@Param("startNum") int startNum);

    public List<PetRegisterDTO> AdminAdopt(@Param("startNum") int startNum);

    public List<PetRegisterDTO> AdminMissing(@Param("startNum") int startNum);

    public int searchPetsCount();

    public int searchMissingCount();

    public int searchAdoptCount();

}
