package co.kr.gaepan.dto.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PetAdoptApplyDTO {

    private int no;
    private int petNo;
    private int status;
    private String uid;
    private String name;
    private String hp;
    private LocalDateTime regDate;
    private String reason;
    private String zip;
    private String addr1;
    private String addr2;

    // 추가필드
    private String thumb;



}
