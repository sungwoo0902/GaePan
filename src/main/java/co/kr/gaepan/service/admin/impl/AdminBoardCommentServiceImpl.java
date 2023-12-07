package co.kr.gaepan.service.admin.impl;

import co.kr.gaepan.dto.admin.GP_AdminBoardDTO;
import co.kr.gaepan.mapper.admin.AdminBoardMapper;
import co.kr.gaepan.mapper.admin.MybatisAdminBoardMapper;
import co.kr.gaepan.mapper.admin.MybatisAdminCommentMapper;
import co.kr.gaepan.repository.admin.AdminBoardRepository;
import co.kr.gaepan.service.admin.AdminBoardCommentService;
import co.kr.gaepan.util.GP_Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AdminBoardCommentServiceImpl implements AdminBoardCommentService {

    private final AdminBoardRepository adminBoardRepository;
    private final AdminBoardMapper adminBoardMapper;
    private final MybatisAdminCommentMapper mybatisAdminComment;
    private final GP_Util gpUtil;

    @Override
    public List<GP_AdminBoardDTO> findComments(GP_AdminBoardDTO dto) throws Exception {
        List<GP_AdminBoardDTO> dtoList = mybatisAdminComment.findComments(dto);
        log.info("findComments : " + dtoList);
        return dtoList;
    }

    @Override
    public void saveComment(GP_AdminBoardDTO dto) throws Exception {
        log.info("Saving comment : " + dto);
        mybatisAdminComment.saveComment(dto);
        log.info("parent : " + dto.getParent());
    }

    @Override
    public void modifyComment(int bno, String comment) throws Exception {
        mybatisAdminComment.modifyComment(bno, comment);
    }

    @Override
    public void deleteComment(int bno) throws Exception {
        mybatisAdminComment.deleteComment(bno);
    }

    @Override
    public int countComments(int bno) throws Exception {
        int countComment = mybatisAdminComment.countComments(bno);
        return countComment;
    }
}
