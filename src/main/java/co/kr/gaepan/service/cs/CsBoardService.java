package co.kr.gaepan.service.cs;

import co.kr.gaepan.dto.board.BoardDTO;
import co.kr.gaepan.dto.cs.PageRequestDTO;
import co.kr.gaepan.dto.cs.PageResponseDTO;
import co.kr.gaepan.mapper.cs.CsBoardMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Log4j2
public class CsBoardService {

    @Autowired
    private CsBoardMapper mapper;

    // 글 목록 조회
    public PageResponseDTO findByGroupAndCate(PageRequestDTO pageRequestDTO){

        log.info("CsBoardService pageRequestDTO : " + pageRequestDTO);
        log.info("CsBoardService getGroup : " + pageRequestDTO.getGroup());
        log.info("CsBoardService getCate : " + pageRequestDTO.getCate());

        // total 값 필요한 이유는 총 페이지 나타내야하므로
        int totalElements = mapper.countByGroupAndCate(pageRequestDTO.getGroup(), pageRequestDTO.getCate());
        log.info("CsBoardService totalElements : " + totalElements);


        List<BoardDTO> dtoList = mapper.findByGroupAndCate(pageRequestDTO.getGroup(), pageRequestDTO.getCate(), pageRequestDTO.getOffset(), pageRequestDTO.getLimit());
        log.info("CsBoardService dtoList : " + dtoList);

        return PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total(totalElements)
                .build();

    }

    // 글 조회
    public BoardDTO findByNo(int bno){

        log.info("CsBoardService findByNo: " + bno);
        return mapper.findByNo(bno);
    }

    // 답변(댓글) 조회
    public List<BoardDTO> findByParent(int bno){

        return mapper.findByParent(bno);
    }

    // 문의하기
    public void insertInquiry(BoardDTO boardDTO){

        log.info("CsBoardService insertInquiry: " + boardDTO);

        mapper.insertInquiry(
                boardDTO.getUid(),
                boardDTO.getTitle(),
                boardDTO.getContent(),
                boardDTO.getCate(),
                boardDTO.getType(),
                boardDTO.getRegIP(),
                boardDTO.getRegDate()
        );
    }

    // 댓글 등록
    public void insertComment(BoardDTO boardDTO){
        mapper.insertComment(
                boardDTO.getUid(),
                boardDTO.getCate(),
                boardDTO.getType(),
                boardDTO.getRegIP(),
                boardDTO.getRegDate(),
                boardDTO.getParent(),
                boardDTO.getComment()
        );
    }

    public void deleteArticle(int bno){

        mapper.deleteArticle(bno);
    }


    public void updateComment(BoardDTO boardDTO){
        mapper.updateComment(boardDTO.getComment(), boardDTO.getBno());
    }
    public void updateArticle(BoardDTO boardDTO){
        mapper.updateArticle(boardDTO.getTitle(), boardDTO.getContent(), boardDTO.getBno());
    }

}
