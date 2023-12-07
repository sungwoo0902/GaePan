package co.kr.gaepan.service.admin.impl;

import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;
import co.kr.gaepan.dto.member.MemberDTO;
import co.kr.gaepan.mapper.admin.MybatisAdminMemberMapper;
import co.kr.gaepan.service.admin.AdminMemberService;
import co.kr.gaepan.util.GP_Util;
import co.kr.gaepan.util.SearchCriteria;
import co.kr.gaepan.util.SearchPageMaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.koreate.common.utils.PageMaker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminMemberServiceImpl implements AdminMemberService {

    private final MybatisAdminMemberMapper mybatisAdminMemberMapper;
    private final GP_Util gpUtil;

    @Override
    public List<MemberDTO> blackList(SearchCriteria cri, int role) throws Exception {
        List<MemberDTO> blackList = mybatisAdminMemberMapper.blackList(cri, role);
        blackList.replaceAll(gpUtil::addrSubString);

        return blackList;
    }

    @Override
    public MemberDTO findById(int no) throws Exception {
        return null;
    }

    @Override
    public void blackMember(List<MemberDTO> memberDTOList) throws Exception {
        mybatisAdminMemberMapper.blackMember(memberDTOList);
    }

    @Override
    public void blackMemberRemove(List<MemberDTO> memberDTOList) throws Exception{
        mybatisAdminMemberMapper.blackMemberRemove(memberDTOList);
    }

    @Override
    public void leaveMember(List<MemberDTO> memberDTO) throws Exception {
        mybatisAdminMemberMapper.leaveMember(memberDTO);
    }

    @Override
    public List<MemberDTO> pagingMemberList(SearchCriteria cri) throws Exception {
        String searchType = cri.getSearchType();
        String word = cri.getKeyword();

        log.info("searchType : " + searchType);
        log.info("keyword : " +word);

        List<MemberDTO> memberList = mybatisAdminMemberMapper.searchList(cri);
        memberList.replaceAll(gpUtil::addrSubString);

//        log.info("memberList : " + memberList);
        return memberList;
    }

    @Override
    public PageMaker getPageMaker(SearchCriteria cri) throws Exception {
        int totalCount = mybatisAdminMemberMapper.searchListCount(cri);

        PageMaker pm = new SearchPageMaker();
        pm.setCri(cri);
        pm.setTotalCount(totalCount);
        return pm;
    }

    @Override
    public PageMaker getPageMaker2(SearchCriteria cri) throws Exception {
        int totalCount = mybatisAdminMemberMapper.blackListCount(cri);

        PageMaker pm = new SearchPageMaker();
        pm.setCri(cri);
        pm.setTotalCount(totalCount);
        return pm;
    }
}
