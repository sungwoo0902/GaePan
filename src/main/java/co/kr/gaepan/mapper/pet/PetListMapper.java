package co.kr.gaepan.mapper.pet;


import co.kr.gaepan.dto.pet.PetCateDTO;
import co.kr.gaepan.dto.pet.PetRegisterDTO;
import co.kr.gaepan.dto.pet.PetTypeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
@Mapper
public interface PetListMapper {

    public List<PetRegisterDTO> mainmissinglist();
    public List<PetRegisterDTO> mainpetdoglist();
    public List<PetRegisterDTO> mainpetcatlist();
    public List<PetRegisterDTO> mainpetetclist();
    public PetRegisterDTO pet(String no);
    public List<PetCateDTO> petcategory();
    public List<PetTypeDTO> petType(int cate);

    List<PetRegisterDTO> searchPets(@Param("searchType") String searchType, @Param("key")  String key, @Param("startNum") int startNum, @Param("division") int division);
    public int searchPetsCount(@Param("searchType") String searchType, @Param("key")  String key, @Param("division") int division);

}
