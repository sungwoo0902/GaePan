package co.kr.gaepan.mapper.admin;

import co.kr.gaepan.dto.pet.PetRegisterDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AdminPetMapperTest {

    @Autowired
    private AdminPetMapper adminPetMapper;

    @Test
    public void AdminPet(){

        int startNum = 1;

        List<PetRegisterDTO> result = adminPetMapper.AdminPet(startNum);

        System.out.println("result: " + result);

    }

}
