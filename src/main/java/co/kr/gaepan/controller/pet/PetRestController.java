package co.kr.gaepan.controller.pet;

import co.kr.gaepan.dto.pet.PetTypeDTO;
import co.kr.gaepan.service.pet.PetBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pet/*")
@RestController
@RequiredArgsConstructor
public class PetRestController {

    private final PetBoardService petBoardService;

    @GetMapping("/types/{cate}")
    @ResponseBody
    public List<PetTypeDTO> getPetTypes(@PathVariable int cate) {

        List<PetTypeDTO> pettypes = petBoardService.pettype(cate);

        return pettypes;
    }

}
