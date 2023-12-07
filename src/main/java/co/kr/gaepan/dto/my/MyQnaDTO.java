package co.kr.gaepan.dto.my;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyQnaDTO {

    private int bno;
    private String uid;
    private String title;
    private String content;
    private String group;
    private int cate;
    private int type;
    private String redIP;
    private LocalDateTime regDate;
    private int hit;
    private String comment;

    // 추가 필드
    private String cateName;
    private String typeName;

}
