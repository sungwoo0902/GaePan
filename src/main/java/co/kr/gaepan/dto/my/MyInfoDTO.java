package co.kr.gaepan.dto.my;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyInfoDTO {

    private int no;
    private String uid;
    private String pw;
    private String name;
    private String nick;
    private int gender;
    private String hp1;
    private String hp2;
    private String hp3;
    private String hp;
    private String email1;
    private String email2;
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
    private String bizRegNum;

    // 추가: email을 업데이트하는 메서드
    public void setEmail(String email1, String email2) {
        this.email1 = email1;
        this.email2 = email2;
        this.email = email1 + "@" + email2;
    }

    // 추가: hp를 업데이트하는 메서드
    public void setHp(String hp1, String hp2, String hp3) {
        this.hp1 = hp1;
        this.hp2 = hp2;
        this.hp3 = hp3;
        this.hp = hp1 + "-" + hp2 + "-" + hp3;
    }

    // regDate를 문자열로 반환하는 메서드
    public String getRegDateAsString() {
        return formatDateTime(regDate);
    }

    // levDate를 문자열로 반환하는 메서드
    public String getLevDateAsString() {
        return formatDateTime(levDate);
    }

    // LocalDateTime을 문자열로 포맷하는 메서드
    private String formatDateTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
