package co.kr.gaepan.service.admin.impl;

import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;
import co.kr.gaepan.dto.board.BoardDTO;
import co.kr.gaepan.entity.admin.GP_AdminBoardEntity;
import co.kr.gaepan.mapper.admin.AdminBoardMapper;
import co.kr.gaepan.mapper.admin.MybatisAdminBoardMapper;
import co.kr.gaepan.repository.admin.AdminBoardRepository;
import co.kr.gaepan.service.admin.AdminBoardService;
import co.kr.gaepan.util.GP_Util;
import co.kr.gaepan.util.SearchCriteria;
import co.kr.gaepan.util.SearchPageMaker;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.koreate.common.utils.PageMaker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminBoardServiceImpl implements AdminBoardService {

    private final AdminBoardRepository adminBoardRepository;
    private final AdminBoardMapper adminBoardMapper;
    private final MybatisAdminBoardMapper mybatisAdminBoardMapper;
    private final GP_Util gpUtil;

    @Override
    public void saveAdminBoard(GP_AdminBoardDTO dto) throws Exception {
        GP_AdminBoardEntity entity = adminBoardMapper.toEntity(dto);
        log.info("Saving admin board : " + entity);
        adminBoardRepository.save(entity);
    }

    @Override
    public List<GP_AdminBoardDTO> pagingBoardList(SearchCriteria cri) throws Exception {
        String searchType = cri.getSearchType();
        String word = cri.getKeyword();

        log.info("searchType : " + searchType);
        log.info("keyword : " +word);

        List<GP_AdminBoardDTO> boardList = mybatisAdminBoardMapper.searchList(cri);
//        boardList.add(GP_AdminBoardDTO.builder().group(group).build());
//        log.info("boardList group : " + boardList.get(1).getGroup());
        return boardList;
    }

    @Override
    public void modifyAdminBoard(GP_AdminBoardDTO dto) throws Exception {
        mybatisAdminBoardMapper.modifyById(replace(dto));
    }

    @Override
    public void deleteAdminBoard(List<GP_AdminBoardDTO> boardDTO) throws Exception {
        mybatisAdminBoardMapper.deleteAdminBoard(boardDTO);
    }

    @Override
    public void deleteById(int bno) throws Exception {
        mybatisAdminBoardMapper.deleteById(bno);
    }

    @Override
    public GP_AdminBoardDTO findById(int bno) throws Exception {
        GP_AdminBoardDTO dto = mybatisAdminBoardMapper.findById(bno);
        return dto;
    }

    @Override
    public List<BoardDTO> findByGroup(String group) throws Exception {
        List<BoardDTO> dto = mybatisAdminBoardMapper.findByGroup(group);
        return dto;
    }


    @Override
    public void updateViewCnt(HttpServletRequest request,
                              HttpServletResponse response,
                              int bno) throws Exception {
        String cookieBno = "GaePan" + bno;
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies) {
                if(cookie.getName().equals(cookieBno)) {
                    // 쿠키값이 존재하면 이대로 return 해서 종료
                    return;
                }
            }
        }
        // 쿠키가 존재하지 않으면 이하 실행
        Cookie cookie = new Cookie(cookieBno, bno+"");
        String path = request.getContextPath();
        cookie.setPath(path);
        cookie.setMaxAge(60*60); // 1시간
        response.addCookie(cookie);
        mybatisAdminBoardMapper.updateViewCnt(bno);
    }

    @Override
    public PageMaker getPageMaker(SearchCriteria cri) throws Exception {
        int totalCount = mybatisAdminBoardMapper.searchListCount(cri);

        PageMaker pm = new SearchPageMaker();
        pm.setCri(cri);
        pm.setTotalCount(totalCount);

        return pm;
    }

    // board에서 글 쓸 때 태그 공격 막기
    public GP_AdminBoardDTO replace(GP_AdminBoardDTO dto){
        dto.setTitle(gpUtil.replaceScript(dto.getTitle()));
        dto.setContent(gpUtil.replaceScript(dto.getContent()));

        return dto;
    }
}
