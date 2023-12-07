package co.kr.gaepan.service.pet;

import co.kr.gaepan.dto.pet.*;
import co.kr.gaepan.entity.pet.PetRegisterEntity;
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
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class PetBoardService {

    private final PetBoardRepository petBoardRepository;
    private final PetFileRepository petFileRepository;
    private final PetListMapper petListMapper;
    private final ModelMapper modelMapper;

    @Value("${spring.servlet.multipart.location}")
    private String filePath;

    public void insertPet(PetRegisterDTO dto) {

        PetRegisterEntity entity = dto.toEntity();

        PetFileDTO fileDTO = fileUpload(dto.getFilethumb()); // 단일 파일 업로드로 수정

        if (fileDTO != null) {
            fileDTO.setNo(entity.getNo());
            log.info("FileDTO : " + fileDTO);

            petFileRepository.save(fileDTO.toEntity());

            entity.setThumb(fileDTO.getSName());

        }

        PetRegisterEntity fileEntity = petBoardRepository.save(entity);
    }

    public PetFileDTO fileUpload(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            String path = new File(filePath).getAbsolutePath();
            String originalFileName = file.getOriginalFilename();
            String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = UUID.randomUUID().toString() + fileExtension;

            try {
                file.transferTo(new File(path, newFileName));
                return PetFileDTO.builder().oName(originalFileName).sName(newFileName).build();
            } catch (IOException e) {
                log.error("파일 업로드 실패: " + e.getMessage(), e);
            }
        }

        return null; // 파일이 비어있거나 업로드 실패 시 null 반환
    }

    public PageResponseDTO PetAll(PageRequestDTO pageRequestDTO, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByDivision(pageable, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> dtoList = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalElements)
                .build();
    }

    public PageResponseDTO PetDog(PageRequestDTO pageRequestDTO, int cate, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByCateAndDivision(pageable, cate, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> petAll = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(petAll)
                .total(totalElements)
                .build();
    }

    public PageResponseDTO PetCat(PageRequestDTO pageRequestDTO, int cate, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByCateAndDivision(pageable, cate, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> petAll = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(petAll)
                .total(totalElements)
                .build();
    }

    public PageResponseDTO PetEtc(PageRequestDTO pageRequestDTO, int cate, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByCateAndDivision(pageable, cate, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> petAll = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(petAll)
                .total(totalElements)
                .build();
    }
    public PageResponseDTO AdoptAll(PageRequestDTO pageRequestDTO, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByDivision(pageable, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> dtoList = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalElements)
                .build();
    }

    public PageResponseDTO AdoptDog(PageRequestDTO pageRequestDTO, int cate, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByCateAndDivision(pageable, cate, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> petAll = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(petAll)
                .total(totalElements)
                .build();
    }

    public PageResponseDTO AdoptCat(PageRequestDTO pageRequestDTO, int cate, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByCateAndDivision(pageable, cate, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> petAll = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(petAll)
                .total(totalElements)
                .build();
    }

    public PageResponseDTO AdoptEtc(PageRequestDTO pageRequestDTO, int cate, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByCateAndDivision(pageable, cate, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> petAll = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(petAll)
                .total(totalElements)
                .build();
    }

    public PageResponseDTO MsAll(PageRequestDTO pageRequestDTO, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByDivision(pageable, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> dtoList = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalElements)
                .build();
    }

    public PageResponseDTO MsDog(PageRequestDTO pageRequestDTO, int cate, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByCateAndDivision(pageable, cate, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> petAll = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(petAll)
                .total(totalElements)
                .build();
    }

    public PageResponseDTO MsCat(PageRequestDTO pageRequestDTO, int cate, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByCateAndDivision(pageable, cate, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> petAll = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(petAll)
                .total(totalElements)
                .build();
    }

    public PageResponseDTO MsEtc(PageRequestDTO pageRequestDTO, int cate, int division) {

        Pageable pageable = pageRequestDTO.getPageable("no");

        Page<PetRegisterEntity> petRegisterEntities = petBoardRepository.findAllByCateAndDivision(pageable, cate, division);

        Page<PetRegisterDTO> articles = petRegisterEntities.map(PetRegisterEntity::toDTO);

        List<PetRegisterDTO> petAll = articles.getContent()
                .stream()
                .map(entity -> modelMapper.map(entity, PetRegisterDTO.class))
                .toList();

        // Fix total elements count retrieval
        int totalElements = (int) articles.getTotalElements();

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(petAll)
                .total(totalElements)
                .build();
    }

    public PetRegisterDTO pet(String no) {

        PetRegisterDTO pet = petListMapper.pet(no);

        return pet;
    }

    public List<PetCateDTO> petcate() {

        List<PetCateDTO> petCates = petListMapper.petcategory();

        return petCates;

    }
    public List<PetTypeDTO> pettype(int cate) {

        List<PetTypeDTO> pettypes = petListMapper.petType(cate);

        return pettypes;

    }

    public List<PetRegisterDTO> MainMissingPet() {

        List<PetRegisterDTO> missing = petListMapper.mainmissinglist();

        return missing;

    }
    public List<PetRegisterDTO> MainPetDogList() {

        List<PetRegisterDTO> MainPetDog = petListMapper.mainpetdoglist();

        return MainPetDog;

    }
    public List<PetRegisterDTO> MainPetCatList() {

        List<PetRegisterDTO> MainPetCat = petListMapper.mainpetcatlist();

        return MainPetCat;

    }
    public List<PetRegisterDTO> MainPetEtcList() {

        List<PetRegisterDTO> MainPetEtc = petListMapper.mainpetetclist();
        return MainPetEtc;

    }

    public List<PetRegisterDTO> searchPets(String searchType, String key, int startNum, int division) {

        return petListMapper.searchPets(searchType, key, startNum, division);

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
