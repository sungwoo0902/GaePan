package co.kr.gaepan.security;


import co.kr.gaepan.dto.member.MemberDTO;
import co.kr.gaepan.service.member.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SecurityUserService implements UserDetailsService {

    private final MemberService service;

    public SecurityUserService(MemberService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 패스워드에 대한 검사는 이전 컴포넌트(AuthenticationProvider)에서 처리되어 사용자 아이디만 넘어옴
        MemberDTO member = service.loginUser(username);
        log.info("member: " + member);

        if (member == null) {
            throw new UsernameNotFoundException("해당 사용자가 없습니다.");
        }

        // 사용자 인증객체 생성(세션에 저장)
        UserDetails userDetails = MyUserDetails.builder()
                .member(member)
                .build();

        log.info("User: " + userDetails);

        // 세션에 사용자 정보 저장
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        // 사용자 객체, 자격증명(비밀번호 등), 권한설정
                        userDetails, userDetails.getPassword(), userDetails.getAuthorities())
        );

        return userDetails;
    }

}