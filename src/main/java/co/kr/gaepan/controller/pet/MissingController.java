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
public class MissingController {

    private final PetBoardService petBoardService;
    private final GP_Util gpUtil;

    @RequestMapping("/pet/missinglist")
    public String missinglist(PageRequestDTO pageRequestDTO , Model model) {

        PageResponseDTO pageResponseDTO = petBoardService.MsAll(pageRequestDTO,2);

        model.addAttribute("pageResponseDTO", pageResponseDTO);
        model.addAttribute("currentNick", gpUtil.getCurrentNick());
        return "pet/missinglist";

    }

    @RequestMapping("/pet/missingdoglist")
    public String missingdoglist(PageRequestDTO pageRequestDTO ,Model model) {
        PageResponseDTO pageResponseDTO = petBoardService.MsDog(pageRequestDTO, 1,2);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "pet/missingdoglist";
    }

    @RequestMapping("/pet/missingcatlist")
    public String missingcatlist(PageRequestDTO pageRequestDTO ,Model model) {
        PageResponseDTO pageResponseDTO = petBoardService.MsCat(pageRequestDTO,2,2);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "pet/missingcatlist";
    }

    @RequestMapping("/pet/missingetclist")
    public String missingetclist(PageRequestDTO pageRequestDTO ,Model model) {
        PageResponseDTO pageResponseDTO = petBoardService.MsEtc(pageRequestDTO,3,2);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "pet/missingetclist";
    }
    @RequestMapping("/pet/missingregister")
    public String missingregister(Model model) {

        List<PetCateDTO> petcate = petBoardService.petcate();
        model.addAttribute("petcate", petcate);
        model.addAttribute("currentNick", gpUtil.getCurrentNick());
        return "pet/missingregister";

    }

    @PostMapping("/pet/missingregister")
    public String insert(PetRegisterDTO dto) {

        petBoardService.insertPet(dto);

        return "redirect:missinglist";
    }

    @RequestMapping("/pet/missingdetails")
    public String blog_details(String no, Model model) {

        PetRegisterDTO petview = petBoardService.pet(no);

        model.addAttribute("petview", petview);

        return "pet/missingdetails";
    }
}
