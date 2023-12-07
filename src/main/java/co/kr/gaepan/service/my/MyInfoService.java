package co.kr.gaepan.service.my;

import co.kr.gaepan.dto.my.MyInfoDTO;
import co.kr.gaepan.mapper.my.MyInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MyInfoService {

    private final MyInfoMapper myInfoMapper;
    private final PasswordEncoder passwordEncoder;

    public MyInfoDTO selectInfo(String uid) {
        return myInfoMapper.selectInfo(uid);
    }

    public int updateInfo(MyInfoDTO myInfoDTO){
        int updatedRowCount = myInfoMapper.updateInfo(myInfoDTO);
        log.info("Updated rows: " + updatedRowCount);
        return updatedRowCount;
    }

    public int updatePassword(String uid, String currentPw, String newPw, String confirmNewPw) {
        // 현재 비밀번호 확인
        MyInfoDTO currentInfo = myInfoMapper.selectInfo(uid);

        if (currentInfo == null) {
            return 0; // 현재 정보가 없음
        }

        // 사용자가 입력한 현재 비밀번호를 암호화하여 데이터베이스에 저장된 암호화된 비밀번호와 비교
        if (!passwordEncoder.matches(currentPw, currentInfo.getPw())) {
            return 0; // 비밀번호 불일치
        }

        // 새로운 비밀번호와 확인용 비밀번호가 일치하는지 확인
        if (!newPw.equals(confirmNewPw)) {
            return 0; // 새로운 비밀번호와 확인용 비밀번호 불일치
        }

        // MyBatis 매퍼를 통해 비밀번호 변경 쿼리 수행
        int rowsAffected = myInfoMapper.updatePassword(uid, passwordEncoder.encode(newPw));

        return rowsAffected;
    }

    @Transactional
    public void deleteInfo(MyInfoDTO myInfoDTO) {
        myInfoMapper.deleteInfo(myInfoDTO);
    }

    @Transactional
    public boolean isNickDuplicate(String uid, String nick) {
        // 닉네임이 중복되면 true, 중복되지 않으면 false 반환
        int count = myInfoMapper.countByNick(nick);
        MyInfoDTO currentInfo = myInfoMapper.selectInfo(uid);

        // 만약 현재 사용자의 닉네임과 변경하려는 닉네임이 같다면 중복으로 처리하지 않음
        return count > 0 && !currentInfo.getNick().equals(nick);
    }

    @Transactional
    public boolean isEmailDuplicate(String uid, String email) {
        // 이메일이 중복되면 true, 중복되지 않으면 false 반환
        int count = myInfoMapper.countByEmail(email);
        MyInfoDTO currentInfo = myInfoMapper.selectInfo(uid);

        // 만약 현재 사용자의 이메일과 변경하려는 이메일이 같다면 중복으로 처리하지 않음
        return count > 0 && !currentInfo.getEmail().equals(email);
    }
}
