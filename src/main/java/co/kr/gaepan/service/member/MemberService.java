package co.kr.gaepan.service.member;

import co.kr.gaepan.dto.member.MemberDTO;
import co.kr.gaepan.mapper.member.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper mapper;

    public MemberDTO loginUser(String uid) {
        return mapper.loginUser(uid);
    }

    public void registerUser(MemberDTO dto) {
        mapper.registerUser(dto);
    }

    public boolean isUserIdUnique(String uid) {
        int count = mapper.checkDuplicateId(uid);
        return count == 0;
    }
    public boolean isUserNickUnique(String nick) {
        int count = mapper.checkDuplicateNick(nick);
        return count == 0;
    }
    public boolean isUserHpUnique(String hp) {
        int count = mapper.checkDuplicateHp(hp);
        return count == 0;
    }
    public boolean isUserEmailUnique(String email) {
        int count = mapper.checkDuplicateEmail(email);
        return count == 0;
    }


}
