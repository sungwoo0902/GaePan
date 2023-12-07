package co.kr.gaepan.mapper.my;

import co.kr.gaepan.dto.my.MyInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MyInfoCheckMapper {

    // 사용자 정보 불러오기
    MyInfoDTO selectInfoCheck(String uid);
}
