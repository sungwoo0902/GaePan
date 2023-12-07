package co.kr.gaepan.service.my;

import co.kr.gaepan.dto.my.MyInfoDTO;
import co.kr.gaepan.mapper.my.MyInfoCheckMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class MyInfoCheckService {

    private final MyInfoCheckMapper myInfoCheckMapper;

    public MyInfoDTO selectInfoCheck(String uid) {
        return myInfoCheckMapper.selectInfoCheck(uid);
    }
}
