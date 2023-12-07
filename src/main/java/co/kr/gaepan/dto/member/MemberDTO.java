package co.kr.gaepan.dto.member;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"pw", "pass1", "rownum"})
public class MemberDTO {

    private int no;
    private String uid;
    private String pw;
    private String name;
    private String nick;
    private int gender;
    private String hp;
    private String email;
    private String birth;
    private int role;
    private String zip;
    private String addr1;
    private String addr2;
    private String exp;
    private int count;
    private LocalDateTime regDate;
    private LocalDateTime levDate;
    
    // 추가
    private String pass1;
    private String company;
    private String bizRegNum;

    // admin에서 추가
    private int rownum;

}
