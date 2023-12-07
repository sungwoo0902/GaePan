package co.kr.gaepan.controller.admin.rest;

import co.kr.gaepan.dto.member.MemberDTO;
import co.kr.gaepan.service.admin.AdminMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/admin/member/*")
public class RestAdminMember {

    private final AdminMemberService adminMemberService;
    @PostMapping("/blackListCheck")
    public String blackListCheck(@RequestBody List<MemberDTO> memberDTOList) {
        try {
            adminMemberService.blackMember(memberDTOList);
        } catch (Exception e) {
            log.error("admin member blackListCheck error", e.getMessage());
            throw new RuntimeException(e);
        }
        // return값을 공백이라도 줘야 ajax에서 success 처리가 됨
        return "";
    }

    @PostMapping("/blackListRemove")
    public String blackListRemove(@RequestBody List<MemberDTO> memberDTOList) {
        try {
            adminMemberService.blackMemberRemove(memberDTOList);
        } catch (Exception e) {
            log.error("admin member blackListCheck error", e.getMessage());
            throw new RuntimeException(e);
        }
        return "";
    }

    @PostMapping("/leaveCheck")
    public String leaveCheck(@RequestBody List<MemberDTO> memberDTO) {
        try {
            adminMemberService.leaveMember(memberDTO);
        } catch (Exception e) {
            log.error("admin member blackListCheck error", e.getMessage());
            throw new RuntimeException(e);
        }
        return "";
    }
}
