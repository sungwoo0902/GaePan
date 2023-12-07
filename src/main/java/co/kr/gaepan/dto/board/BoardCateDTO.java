package co.kr.gaepan.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCateDTO {

    private int cno;
    private int cate;
    private String cateName;


}
