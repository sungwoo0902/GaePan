package co.kr.gaepan.controller.admin.animal;

import co.kr.gaepan.dto.pet.PetRegisterDTO;
import co.kr.gaepan.mapper.admin.AdminPetMapper;
import co.kr.gaepan.service.admin.AdminPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/admin/animal/*")
@Controller
public class AdminAnimalController {

    @Autowired
    private AdminPetService adminPetService;

    @Autowired
    private AdminPetMapper adminPetMapper;

    @GetMapping("/pet")
    public String pet(Model model, @RequestParam(defaultValue = "1") String pg) {

        int currentPage = adminPetService.getCurrentPage(pg);

        int totalPages = adminPetMapper.searchMissingCount();

        int lastPage = adminPetService.getLastPageNum(totalPages);

        int[] result = adminPetService.getPageGroupNum(currentPage, lastPage);

        int pageStartNum = adminPetService.getPageStartNum(currentPage,totalPages);

        int startNum = adminPetService.getStartNum(currentPage);

        int pageGroupStart = result[0];
        int pageGroupEnd = result[1];

        List<PetRegisterDTO> Adminpet = adminPetService.adminpet(startNum);

        model.addAttribute("Adminpet", Adminpet);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageGroupStart", pageGroupStart);
        model.addAttribute("pageGroupEnd", pageGroupEnd);
        model.addAttribute("pageStartNum", pageStartNum + 1);

        return "admin/animal/pet";

    }

    @GetMapping("/adopt")
    public String adopt(Model model, @RequestParam(defaultValue = "1") String pg) {

        int currentPage = adminPetService.getCurrentPage(pg);

        int totalPages = adminPetMapper.searchAdoptCount();

        int lastPage = adminPetService.getLastPageNum(totalPages);

        int[] result = adminPetService.getPageGroupNum(currentPage, lastPage);

        int pageStartNum = adminPetService.getPageStartNum(currentPage,totalPages);

        int startNum = adminPetService.getStartNum(currentPage);

        int pageGroupStart = result[0];
        int pageGroupEnd = result[1];

        List<PetRegisterDTO> Adminadopt = adminPetService.adminadopt(startNum);

        model.addAttribute("Adminadopt", Adminadopt);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageGroupStart", pageGroupStart);
        model.addAttribute("pageGroupEnd", pageGroupEnd);
        model.addAttribute("pageStartNum", pageStartNum + 1);

        return "admin/animal/adopt";

    }

    @GetMapping("/missing")
    public String company(Model model, @RequestParam(defaultValue = "1") String pg) {

        int currentPage = adminPetService.getCurrentPage(pg);

        int totalPages = adminPetMapper.searchMissingCount();

        int lastPage = adminPetService.getLastPageNum(totalPages);

        int[] result = adminPetService.getPageGroupNum(currentPage, lastPage);

        int pageStartNum = adminPetService.getPageStartNum(currentPage,totalPages);

        int startNum = adminPetService.getStartNum(currentPage);

        int pageGroupStart = result[0];
        int pageGroupEnd = result[1];

        List<PetRegisterDTO> Adminmissing = adminPetService.adminmissing(startNum);

        model.addAttribute("Adminmissing", Adminmissing);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("lastPage", lastPage);
        model.addAttribute("pageGroupStart", pageGroupStart);
        model.addAttribute("pageGroupEnd", pageGroupEnd);
        model.addAttribute("pageStartNum", pageStartNum + 1);

        return "admin/animal/missing";

    }
}
