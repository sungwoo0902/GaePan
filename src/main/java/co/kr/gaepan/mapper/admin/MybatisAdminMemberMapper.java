package co.kr.gaepan.mapper.admin;

import co.kr.gaepan.dto.member.MemberDTO;
import co.kr.gaepan.util.SearchCriteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MybatisAdminMemberMapper {
    // 모든 회원 정보 조회
    List<MemberDTO> findAll() throws Exception;

    // 블랙리스트 회원 정보 조회
    List<MemberDTO> blackList(SearchCriteria cri, int role) throws Exception;

    // 블랙리스트 등록
    void blackMember(List<MemberDTO> memberDTOList) throws Exception;

    // 블랙리스트 해제
    void blackMemberRemove(List<MemberDTO> memberDTOList) throws Exception;

    // 탈퇴한 회원 목록
    void leaveMember(List<MemberDTO> memberDTO) throws Exception;

    // 검색된 회원 목록
    List<MemberDTO> searchList(SearchCriteria cri) throws Exception;

    // 검색된 회원 전체 개수
    int searchListCount(SearchCriteria cri) throws Exception;

    // 블랙 리스트 페이징 처리
    int blackListCount(SearchCriteria cri) throws Exception;
}
