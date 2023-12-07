package co.kr.gaepan.service.admin;

import co.kr.gaepan.dto.member.MemberDTO;
import co.kr.gaepan.util.SearchCriteria;
import net.koreate.common.utils.PageMaker;

import java.util.List;

public interface AdminMemberService {

    // 블랙리스트
    List<MemberDTO> blackList(SearchCriteria cri, int role) throws Exception;

    // 회원 상세 보기
    MemberDTO findById(int no) throws Exception;

    // 블랙리스트 등록 / 등급 수정 등
    void blackMember(List<MemberDTO> memberDTOList) throws Exception;

    // 블랙리스트 해제
    void blackMemberRemove(List<MemberDTO> memberDTOList) throws Exception;

    // 회원 탈퇴 -> 개인정보 null / uid, nick은 그대로 유지
    void leaveMember(List<MemberDTO> memberDTO) throws Exception;

    // 검색 결과에 따른 회원 페이징 처리된 목록
    List<MemberDTO> pagingMemberList(SearchCriteria cri) throws Exception;

    // 페이징 블럭 정보
    PageMaker getPageMaker(SearchCriteria cri) throws Exception;

    // 블랙리스트 페이징 블럭
    PageMaker getPageMaker2(SearchCriteria cri) throws Exception;
}
