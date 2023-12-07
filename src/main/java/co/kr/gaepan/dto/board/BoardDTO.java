package co.kr.gaepan.dto.board;

import co.kr.gaepan.entity.admin.GP_AdminBoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardDTO {

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
    
    // 추가필드(JOIN 한 후에 데이터 조회시 필요)
    private String cateName;
    private String typeName;


    // toEntity
    public GP_AdminBoardEntity toEntity(){
        return GP_AdminBoardEntity.builder()
                .bno(bno)
                .uid(uid)
                .title(title)
                .content(content)
                .group(group)
                .cate(cate)
                .type(type)
                .regIP(regIP)
                .regDate(regDate)
                .hit(hit)
                .parent(parent)
                .comment(comment)
                .build();
    }
}
