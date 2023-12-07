package co.kr.gaepan.mapper.my;

import co.kr.gaepan.dto.my.MyInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MyInfoMapper {

    // 사용자 정보 불러오기
    MyInfoDTO selectInfo(String uid);

    // 사용자 정보 수정하기
    int updateInfo(MyInfoDTO myInfoDTO);

    // 비밀번호 수정하기
    int updatePassword(@Param("uid") String uid, @Param("newPw") String newPw);

    // 사용자 정보 삭제하기
    void deleteInfo(MyInfoDTO myInfoDTO);

    // 닉네임 중복검사
    int countByNick(@Param("nick") String nick);

    // 이메일 중복 검사
    int countByEmail(@Param("email") String eamil);
}
