package co.kr.gaepan.dto.cs;

import co.kr.gaepan.dto.board.BoardDTO;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString //DTO를 문자열로 log 출력할 수 있음
@Data
public class PageResponseDTO {


    private List<BoardDTO> dtoList;
    private String group;
    private int cate;

    // 페이징 요소
    private int offset;
    private int limit;
    private int pg;
    private int size;
    private int total;
    private int start, end;
    private boolean prev, next;

    @Builder
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<BoardDTO> dtoList, int total){

        // Builder를 이용해서 PageRequestDTO 받은 것을 PageResponseDTO의 속성으로 지정해주기
        this.group = pageRequestDTO.getGroup();
        this.cate = pageRequestDTO.getCate();
        this.offset = pageRequestDTO.getOffset();
        this.limit = pageRequestDTO.getLimit();
        this.pg = pageRequestDTO.getPg();
        this.size = pageRequestDTO.getSize();
        this.total = total;
        this.dtoList = dtoList;


        this.end = (int) (Math.ceil(this.pg / 10.0)) * 10;
        this.start = this.end - 9;
        int last = (int) (Math.ceil(total / (double) size));

        this.end = end > last ? last : end; // 삼항연산자
        this.prev = this.start > 1;
        this.next = total > this.end * this.size;


    }
}
