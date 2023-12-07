package co.kr.gaepan.service.admin;

import co.kr.gaepan.dto.pet.*;
import co.kr.gaepan.entity.pet.PetRegisterEntity;
import co.kr.gaepan.mapper.admin.AdminPetMapper;
import co.kr.gaepan.mapper.pet.PetListMapper;
import co.kr.gaepan.repository.pet.PetBoardRepository;
import co.kr.gaepan.repository.pet.PetFileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class AdminPetService {

    private final AdminPetMapper adminPetMapper;
    private final ModelMapper modelMapper;

    public List<PetRegisterDTO> adminpet(int startNum) {

        List<PetRegisterDTO> adminpet = adminPetMapper.AdminPet(startNum);

        return adminpet;

    }

    public List<PetRegisterDTO> adminadopt(int startNum) {

        List<PetRegisterDTO> adminadopt = adminPetMapper.AdminAdopt(startNum);

        return adminadopt;

    }

    public List<PetRegisterDTO> adminmissing(int startNum) {

        List<PetRegisterDTO> adminmissing = adminPetMapper.AdminMissing(startNum);

        return adminmissing;

    }

    public int getLastPageNum(int total) {

        int lastPageNum = 0;

        if(total % 10 == 0){
            lastPageNum = total / 10;
        }else{
            lastPageNum = total / 10 + 1;
        }

        return lastPageNum;
    }

    // 페이지 그룹
    public int[] getPageGroupNum(int currentPage, int lastPageNum) {
        int currentPageGroup = (int)Math.ceil(currentPage / 10.0);
        int pageGroupStart = (currentPageGroup - 1) * 10 + 1;
        int pageGroupEnd = currentPageGroup * 10;

        if(pageGroupEnd > lastPageNum){
            pageGroupEnd = lastPageNum;
        }

        int[] result = {pageGroupStart, pageGroupEnd};

        return result;
    }

    // 페이지 시작번호
    public int getPageStartNum(int total, int currentPage) {
        int start = (currentPage - 1) * 10;
        return total - start;
    }

    // 현재 페이지 번호
    public int getCurrentPage(String pg) {
        int currentPage = 1;

        if(pg != null){
            currentPage = Integer.parseInt(pg);
        }

        return currentPage;
    }

    // Limit 시작번호
    public int getStartNum(int currentPage) {
        return (currentPage - 1) * 10;
    }


}
