package co.kr.gaepan.controller.pet;

import co.kr.gaepan.dto.pet.*;
import co.kr.gaepan.mapper.pet.PetListMapper;
import co.kr.gaepan.service.pet.PetBoardService;
import co.kr.gaepan.util.GP_Util;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Division;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RequestMapping("/pet/*")
@Controller
@RequiredArgsConstructor
public class PetController {

    private final PetBoardService petBoardService;
    private final PetListMapper petListMapper;
    private final GP_Util gpUtil;

    @RequestMapping("/pet/petlist")
    public String main(PageRequestDTO pageRequestDTO , Model model) {

        PageResponseDTO pageResponseDTO = petBoardService.PetAll(pageRequestDTO,1);

        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "pet/petlist";

    }

    @RequestMapping("/pet/petdoglist")
    public String petdoglist(PageRequestDTO pageRequestDTO ,Model model) {
        PageResponseDTO pageResponseDTO = petBoardService.PetDog(pageRequestDTO, 1,1);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "pet/petdoglist";
    }

    @RequestMapping("/pet/petcatlist")
    public String petcatlist(PageRequestDTO pageRequestDTO ,Model model) {
        PageResponseDTO pageResponseDTO = petBoardService.PetCat(pageRequestDTO,2,1);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "pet/petcatlist";
    }

    @RequestMapping("/pet/petetclist")
    public String petetclist(PageRequestDTO pageRequestDTO ,Model model) {
        PageResponseDTO pageResponseDTO = petBoardService.PetEtc(pageRequestDTO,3,1);
        model.addAttribute("pageResponseDTO", pageResponseDTO);

        return "pet/petetclist";
    }




    @RequestMapping("/pet/details")
    public String blog_details(String no, Model model) {

        PetRegisterDTO petview = petBoardService.pet(no);

        model.addAttribute("petview", petview);

        return "pet/details";
    }

    @RequestMapping("/pet/register")
    public String register(Model model) {

        List<PetCateDTO> petcate = petBoardService.petcate();
        model.addAttribute("petcate", petcate);
        model.addAttribute("currentNick", gpUtil.getCurrentNick());
        return "pet/register";

    }

    @PostMapping("/pet/register")
    public String insert(PetRegisterDTO dto) {

        petBoardService.insertPet(dto);

        return "redirect:petlist";
    }

    @GetMapping("/pet/searchpetlist")
    public String searchPets(@RequestParam String searchType,
                             @RequestParam String key,
                             @RequestParam(defaultValue = "1") String pg,
                             @RequestParam(defaultValue = "1") int division,
                             Model model) {
        log.info("searchType : " + searchType);
        log.info("key : " + key);

        int currentPage = petBoardService.getCurrentPage(pg);

        int totalPages = petListMapper.searchPetsCount(searchType, key, division);

        int lastPage = petBoardService.getLastPageNum(totalPages);

        int[] result = petBoardService.getPageGroupNum(currentPage, lastPage);

        int pageStartNum = petBoardService.getPageStartNum(currentPage,totalPages);

        int startNum = petBoardService.getStartNum(currentPage);

        int pageGroupStart = result[0];
        int pageGroupEnd = result[1];

        List<PetRegisterDTO> petList = petBoardService.searchPets(searchType, key, startNum, division);
        model.addAttribute("petList", petList);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageGroupStart", pageGroupStart);
        model.addAttribute("pageGroupEnd", pageGroupEnd);
        model.addAttribute("pageStartNum", pageStartNum + 1);
        model.addAttribute("searchType", searchType);
        model.addAttribute("key", key);
        model.addAttribute("division", division);

        log.info("petList : " + petList);

        return "pet/searchpetlist";
    }


}
