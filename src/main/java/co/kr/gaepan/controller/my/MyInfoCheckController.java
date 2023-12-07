package co.kr.gaepan.controller.my;

import co.kr.gaepan.dto.my.MyInfoDTO;
import co.kr.gaepan.security.MyUserDetails;
import co.kr.gaepan.service.my.MyInfoCheckService;
import co.kr.gaepan.service.my.MyInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/my/*")
@Log4j2
public class MyInfoCheckController {

    @Autowired
    private MyInfoCheckService myInfoCheckService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/infoCheck")
    public String view(Model model, @RequestParam(name = "emailError", defaultValue = "false") boolean emailError) {
        try {
            // 현재 사용자의 Authentication 객체를 가져옴
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                // Principal이 문자열인 경우 처리
                if (authentication.getPrincipal() instanceof String) {
                    // 현재 사용자의 UID를 가져옴
                    String uid = (String) authentication.getPrincipal();
                    MyInfoDTO myInfoDTO = myInfoCheckService.selectInfoCheck(uid);
                    model.addAttribute("myInfoDTO", myInfoDTO);
                } else if (authentication.getPrincipal() instanceof MyUserDetails) {
                    // Principal이 MyUserDetails 타입인 경우
                    MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                    String uid = userDetails.getMember().getUid();
                    MyInfoDTO myInfoDTO = myInfoCheckService.selectInfoCheck(uid);
                    model.addAttribute("myInfoDTO", myInfoDTO);
                }
            }

            // 이메일 에러가 있다면 모델에 추가
            model.addAttribute("emailError", emailError);

        } catch (Exception e) {
            // 예외 처리: 로그 등을 활용하여 디버깅 또는 로깅 가능
            e.printStackTrace();
            // 예외 발생 시 사용자에게 알림 등을 추가할 수 있음
            model.addAttribute("error", "오류가 발생했습니다.");
        }

        return "my/infoCheck";
    }

    @PostMapping("/infoCheck")
    public String passCheck(@RequestParam("uid") String uid,
                            @RequestParam("email") String email,
                            @RequestParam("pw") String enteredPassword,
                            RedirectAttributes redirectAttributes) {
        try {
            // 입력된 비밀번호가 올바른지 확인
            MyInfoDTO myInfoDTO = myInfoCheckService.selectInfoCheck(uid);
            String storedPassword = myInfoDTO.getPw(); // getPw 메서드를 사용하여 저장된 비밀번호 가져옴

            if (passwordEncoder.matches(enteredPassword, storedPassword)) {
                // 비밀번호 확인이 성공하면 http://localhost:8080/my/info로 리다이렉트
                return "redirect:/my/info";
            } else {
                // 비밀번호가 올바르지 않은 경우 이메일 에러 파라미터를 추가하여 정보 확인 페이지로 리다이렉트
                redirectAttributes.addAttribute("emailError", true);
                return "redirect:/my/infoCheck";
            }

        } catch (Exception e) {
            // 예외 처리가 필요한 경우
            e.printStackTrace();
            return "redirect:/my/infoCheck";
        }
    }
}