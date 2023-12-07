package co.kr.gaepan.mapper.member;

import co.kr.gaepan.dto.member.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public MemberDTO loginUser(String uid);

    public void registerUser(MemberDTO dto);

    public int checkDuplicateId(String uid);
    public int checkDuplicateNick(String nick);
    public int checkDuplicateHp(String hp);
    public int checkDuplicateEmail(String email);

}
