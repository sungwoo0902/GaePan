package co.kr.gaepan.controller;

import co.kr.gaepan.dto.pet.PetRegisterDTO;
import co.kr.gaepan.service.pet.PetBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired

    private PetBoardService petBoardService;

    @GetMapping(value = {"/index", "/"})
    public String index(Model model) {

        List<PetRegisterDTO> MainMissingPet = petBoardService.MainMissingPet();
        model.addAttribute("MainMissingPet", MainMissingPet);

        List<PetRegisterDTO> MainPetDog = petBoardService.MainPetDogList();
        model.addAttribute("MainPetDog", MainPetDog);

        List<PetRegisterDTO> MainPetCat = petBoardService.MainPetCatList();
        model.addAttribute("MainPetCat", MainPetCat);

        List<PetRegisterDTO> MainPetEtc = petBoardService.MainPetEtcList();
        model.addAttribute("MainPetEtc", MainPetEtc);

        return "index";
    }

}
