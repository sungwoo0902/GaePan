package co.kr.gaepan.util;

import co.kr.gaepan.dto.member.MemberDTO;
import co.kr.gaepan.security.MyUserDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class GP_Util {

    // < > 특수 문자 치환 -> XSS(Cross-Site Scripting)  공격 막는거임
    public String replaceScript(String text){
        text = text.replace("<", "&lt");
        text = text.replace(">", "&gt");

        return text;
    }

    // 주소 지역만 가져옴
    public MemberDTO addrSubString(MemberDTO memberDTO) {
        memberDTO.setAddr1(addrSubString(memberDTO.getAddr1()));
        return memberDTO;
    }
    // 주소 2글자만 짜르기
    public String addrSubString(String addr){
        return addr.substring(0, 2);
    }

    // 로그인 된 객체의 nick과 매개변수 nick을 비교 
    public boolean checkAuthorization(String checkAuthorNick) {
        // 현재 로그인한 사용자의 정보를 가져옴
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();

        log.info("checkAuthorization checkAuthorNick : " + checkAuthorNick);
        if (authentication.getPrincipal() instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            log.info("checkAuthorization userDetails : " + userDetails);
            String currentNick = userDetails.getMember().getNick();

            log.info("checkAuthorization currentNick : " + currentNick);
            // 현재 사용자와 게시글 작성자의 닉네임을 비교하여 권한 확인
            return currentNick.equals(checkAuthorNick);
        }
        return false;
    }

    // 로그인 된 nick만 가져옴
    public String getCurrentNick() {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            return userDetails.getMember().getNick();
        }
        // 예외처리 해주긴 해야됨..
        return null;
    }

    public int getCurrentRole() {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            return userDetails.getMember().getRole();
        }
        // 예외처리 해주긴 해야됨..
        return 0;
    }

    // 로그인 된 uid만 가져옴
    public String getCurrentUsername() {
        Object principal
                = SecurityContextHolder.getContext()
                                .getAuthentication()
                                .getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }
}
