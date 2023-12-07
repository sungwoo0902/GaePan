package co.kr.gaepan.mapper.pet;

import co.kr.gaepan.dto.pet.PetRegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PetListMapperTest {

    @Autowired
    private PetListMapper petListMapper;

    @Test
    public void searchPets(){

        String searchType = "tName";
        String key = "말티즈";
        int startNum = 1;
        int division = 1;

        List<PetRegisterDTO> result = petListMapper.searchPets(searchType, key, startNum, division);

        System.out.println("result: " + result);

    }

}
