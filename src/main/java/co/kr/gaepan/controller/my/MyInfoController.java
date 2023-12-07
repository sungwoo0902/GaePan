package co.kr.gaepan.controller.my;

import co.kr.gaepan.dto.my.MyInfoDTO;
import co.kr.gaepan.security.MyUserDetails;
import co.kr.gaepan.service.my.MyInfoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/my/*")
@Log4j2
public class MyInfoController {

    @Autowired
    private MyInfoService infoService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 추가: 닉네임 중복 검사를 위한 서비스
    @Autowired
    private MyInfoService myInfoService;

    // 추가: RestTemplate을 사용하기 위한 Bean 정의
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping("/info")
    public String view(Model model, @RequestParam(name = "emailError", defaultValue = "false") boolean emailError) {
        try {
            // 현재 사용자의 Authentication 객체를 가져옴
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null && authentication.isAuthenticated()) {
                // Principal이 문자열인 경우 처리
                if (authentication.getPrincipal() instanceof String) {
                    // 현재 사용자의 UID를 가져옴
                    String uid = (String) authentication.getPrincipal();
                    MyInfoDTO myInfoDTO = infoService.selectInfo(uid);
                    model.addAttribute("myInfoDTO", myInfoDTO);
                } else if (authentication.getPrincipal() instanceof MyUserDetails) {
                    // Principal이 MyUserDetails 타입인 경우
                    MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                    String uid = userDetails.getMember().getUid();
                    MyInfoDTO myInfoDTO = infoService.selectInfo(uid);
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

        return "my/info";
    }

    @PostMapping("/info")
    public String modify(@ModelAttribute MyInfoDTO myInfoDTO, @RequestParam("email1") String email1, @RequestParam("email2") String email2, RedirectAttributes redirectAttributes) {

        // 서비스 메서드 호출
        int updatedRows = infoService.updateInfo(myInfoDTO);

        return "redirect:/member/login";
    }

    @GetMapping("/check/email/{newEmail}")
    @ResponseBody
    public boolean isEmailDuplicate(@PathVariable String newEmail) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = null;

        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof String) {
                uid = (String) authentication.getPrincipal();
            } else if (authentication.getPrincipal() instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                uid = userDetails.getMember().getUid();
            }
        }

        return myInfoService.isEmailDuplicate(uid, newEmail);
    }

    @GetMapping("/check/nick/{newNick}")
    @ResponseBody
    public boolean isNickDuplicate(@PathVariable String newNick) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = null;

        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof String) {
                uid = (String) authentication.getPrincipal();
            } else if (authentication.getPrincipal() instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                uid = userDetails.getMember().getUid();
            }
        }

        return myInfoService.isNickDuplicate(uid, newNick);
    }


    @GetMapping("/passcheck")
    public String passCheck(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof String) {
                String uid = (String) authentication.getPrincipal();
                MyInfoDTO myInfoDTO = infoService.selectInfo(uid);
                model.addAttribute("myInfoDTO", myInfoDTO);
            } else if (authentication.getPrincipal() instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                String uid = userDetails.getMember().getUid();
                MyInfoDTO myInfoDTO = infoService.selectInfo(uid);
                model.addAttribute("myInfoDTO", myInfoDTO);
            }
        }

        return "my/passcheck";
    }

    @GetMapping("/passchange")
    public String passchange(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof String) {
                String uid = (String) authentication.getPrincipal();
                MyInfoDTO myInfoDTO = infoService.selectInfo(uid);
                model.addAttribute("myInfoDTO", myInfoDTO);
            } else if (authentication.getPrincipal() instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                String uid = userDetails.getMember().getUid();
                MyInfoDTO myInfoDTO = infoService.selectInfo(uid);
                model.addAttribute("myInfoDTO", myInfoDTO);
            }
        }

        return "my/passchange";
    }

    @PostMapping("/passchange")
    @ResponseBody
    public Map<String, Object> passmodify(@ModelAttribute("myInfoDTO") MyInfoDTO myInfoDTO,
                                          @RequestParam("currentPw") String currentPw,
                                          @RequestParam("newPw") String newPw,
                                          @RequestParam("confirmNewPw") String confirmNewPw) {

        log.info("passmodify...1");

        Map<String, Object> response = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("passmodify...2");
        if (authentication != null && authentication.isAuthenticated()) {
            log.info("passmodify...3");
            if (authentication.getPrincipal() instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
                String uid = userDetails.getMember().getUid();
                MyInfoDTO storedInfo = infoService.selectInfo(uid);
                log.info("passmodify...4");
                if (storedInfo != null && passwordEncoder.matches(currentPw, storedInfo.getPw())) {
                    // 현재 비밀번호가 일치하면 변경
                    int rowsAffected = infoService.updatePassword(uid, currentPw, newPw, confirmNewPw);
                    log.info("passmodify...5");
                    if (rowsAffected > 0) {
                        response.put("success", true);
                        response.put("message", "비밀번호 변경 성공");
                        log.info("passmodify...6");
                        return response;

                    } else {
                        response.put("success", false);
                        response.put("message", "비밀번호 변경 실패");
                        log.info("passmodify...7");
                    }
                } else {
                    response.put("success", false);
                    response.put("message", "비밀번호가 일치하지 않습니다.");
                    log.info("passmodify...8");
                }
            }
        }

        return response;
    }

    @PostMapping("/passcheck")
    public String userDelete(
            @AuthenticationPrincipal UserDetails userDetails,
            @ModelAttribute MyInfoDTO myInfoDTO,
            RedirectAttributes redirectAttributes) {

        // 사용자 정보 확인
        String username = userDetails.getUsername();
        log.info("username : " + username);
        log.info("pw : " + myInfoDTO.getPw());

        // 패스워드 확인
        if (myInfoDTO.getPw() != null && passwordEncoder.matches(myInfoDTO.getPw(), userDetails.getPassword())) {
            // 로그인된 계정 로그아웃 처리
            SecurityContextHolder.clearContext();

            // 회원 정보 삭제
            infoService.deleteInfo(myInfoDTO);
            log.info("deleteInfo : " + infoService);

            // 로그아웃 성공 메시지 전달
            redirectAttributes.addFlashAttribute("logoutSuccessMessage", "회원 정보가 성공적으로 삭제되었습니다. 다시 로그인해주세요.");
        } else {
            // 패스워드 불일치 시 메시지 전달
            redirectAttributes.addFlashAttribute("deleteErrorMessage", "패스워드가 일치하지 않습니다. 다시 확인해주세요.");
            log.info("logoutSuccessMessage : " + redirectAttributes);
            log.info("deleteErrorMessage : " + redirectAttributes);
        }


        return "redirect:/member/login";
    }



}
