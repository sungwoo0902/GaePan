package co.kr.gaepan.controller.member;

import co.kr.gaepan.dto.member.MemberDTO;
import co.kr.gaepan.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RequestMapping("/member/*")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService service;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(Model model, String success) {
        log.info("success : "+ success);
        model.addAttribute("success",success);
        return "member/login";
    }

    @GetMapping("/register")
    public String register() {
        return "member/register";
    }

    @PostMapping("/register")
    public String register(MemberDTO dto) {

        String rawPw = "";
        String encodePw = "";

        rawPw = dto.getPass1();
        encodePw = passwordEncoder.encode(rawPw);
        dto.setPass1(encodePw);

        log.info("dto" + dto);
        service.registerUser(dto);
        return "member/login";
    }

    @GetMapping("/check/uid/{uid}")
    public ResponseEntity<String> checkDuplicateUserId(@PathVariable String uid) {
        if (service.isUserIdUnique(uid)) {
            return ResponseEntity.ok("사용 가능한 아이디입니다.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 사용 중인 아이디입니다.");
        }
    }

    @GetMapping("/check/nick/{nick}")
    public ResponseEntity<String> checkDuplicateUserNick(@PathVariable String nick) {



        if (service.isUserNickUnique(nick)) {
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 닉네임입니다.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 사용 중인 닉네임입니다.");
        }
    }

    @GetMapping("/check/hp/{hp}")
    public ResponseEntity<String> checkDuplicateUserHp(@PathVariable String hp) {
        if (service.isUserHpUnique(hp)) {
            return ResponseEntity.status(HttpStatus.OK).body("사용 가능한 휴대포");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 사용 중인 휴대폰번호입니다.");
        }
    }

    @GetMapping("/check/email/{email}")
    public ResponseEntity<String> checkDuplicateUserEmail(@PathVariable String email) {
        if (service.isUserEmailUnique(email)) {
            return ResponseEntity.ok("사용 가능한 이메일입니다.");
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 사용 중인 이메일입니다.");
        }
    }
}
