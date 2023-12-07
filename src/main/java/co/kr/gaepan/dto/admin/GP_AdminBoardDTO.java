package co.kr.gaepan.dto.admin;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GP_AdminBoardDTO {

    private int bno;
    private String uid;
    private String title;
    private String content;
    private String group;
    private int cate;
    private int type;
    private String regIP;

    private LocalDateTime regDate;

    private int hit;
    private int parent;
    private String comment;

    // 추가 필드
    private String cateName;
    private String typeName;
    private int rownum;
    private String nick;

    private int cbno;
    private String cComment;
    private LocalDateTime cRegDate;
    private int commentCount;
}
