package co.kr.gaepan.dto.admin;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GP_AdminBoardCateDTO {

    private int cno;
    private int cate;
    private String cateName;
}
