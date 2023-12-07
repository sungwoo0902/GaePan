package co.kr.gaepan.controller.pet;

import co.kr.gaepan.dto.pet.PageRequestDTO;
import co.kr.gaepan.dto.pet.PageResponseDTO;
import co.kr.gaepan.dto.pet.PetCateDTO;
import co.kr.gaepan.dto.pet.PetRegisterDTO;
import co.kr.gaepan.service.pet.PetBoardService;
import co.kr.gaepan.util.GP_Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/pet/*")
@Controller
@RequiredArgsConstructor
public class AdoptController {

    private final PetBoardService petBoardService;
    private final GP_Util gpUtil;

    @RequestMapping("/pet/adoptdiary")
    public String adoptdiary(PageRequestDTO pageRequestDTO , Model model) {

        PageResponseDTO pageResponseDTO = petBoardService.AdoptAll(pageRequestDTO,3);

        model.addAttribute("pageResponseDTO", pageResponseDTO);
        model.addAttribute("currentNick", gpUtil.getCurrentNick());
        return "pet/adoptdiary";

    }

    @RequestMapping("/pet/adoptdogdiary")
    public String adoptdogdiary(PageRequestDTO pageRequestDTO ,Model model) {
        PageResponseDTO pageResponseDTO = petBoardService.AdoptDog(pageRequestDTO, 1,3);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "pet/adoptdogdiary";
    }

    @RequestMapping("/pet/adoptcatdiary")
    public String adoptcatdiary(PageRequestDTO pageRequestDTO ,Model model) {
        PageResponseDTO pageResponseDTO = petBoardService.AdoptCat(pageRequestDTO,2,3);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "pet/adoptcatdiary";
    }

    @RequestMapping("/pet/adoptetcdiary")
    public String adoptetcdiary(PageRequestDTO pageRequestDTO ,Model model) {
        PageResponseDTO pageResponseDTO = petBoardService.AdoptEtc(pageRequestDTO,3,3);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "pet/adoptetcdiary";
    }

    @RequestMapping("/pet/adoptdetails")
    public String blog_details(String no, Model model) {

        PetRegisterDTO petview = petBoardService.pet(no);

        model.addAttribute("petview", petview);

        return "pet/adoptdetails";
    }

    @RequestMapping("/pet/adoptdiaryregister")
    public String adoptdiaryregister(Model model) {

        List<PetCateDTO> petcate = petBoardService.petcate();
        model.addAttribute("petcate", petcate);
        model.addAttribute("currentNick", gpUtil.getCurrentNick());
        return "pet/adoptdiaryregister";
    }

    @PostMapping("/pet/adoptregister")
    public String insert(PetRegisterDTO dto) {

        petBoardService.insertPet(dto);

        return "redirect:adoptdiary";
    }

}
