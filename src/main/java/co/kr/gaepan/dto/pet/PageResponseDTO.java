package co.kr.gaepan.dto.pet;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PageResponseDTO {

    private String cate;
    private List<PetRegisterDTO> dtoList;
    private int pg;
    private int size;
    private int total;

    private int start, end;
    private boolean prev, next;

    @Builder
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<PetRegisterDTO> dtoList, int total) {

        this.cate = pageRequestDTO.getCate();
        this.pg = pageRequestDTO.getPg();
        this.size =  pageRequestDTO.getSize();
        this.dtoList = dtoList;
        this.total = total;

        this.end = (int) (Math.ceil(this.pg/10.0)) * 10;
        this.start = this.end - 9;
        int last = (int) (Math.ceil(total / (double) size));

        this.end = end > last ? last : end;
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;

    }

}

