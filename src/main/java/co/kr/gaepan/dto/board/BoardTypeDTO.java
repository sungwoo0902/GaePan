package co.kr.gaepan.dto.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardTypeDTO {

    private int tno;
    private int cate;
    private int type;
    private String typeName;
}
