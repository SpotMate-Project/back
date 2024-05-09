package capstone.project.SpotMate.controller;


import capstone.project.SpotMate.configure.utils.response.ApiResponse;
import capstone.project.SpotMate.dto.AuthCodeDTO;
import capstone.project.SpotMate.dto.EmailRequestDTO;
import capstone.project.SpotMate.service.EmailAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static capstone.project.SpotMate.configure.utils.Validation.isValidEmail;


@RestController
@RequestMapping("/user/auth")
public class EmailAuthController {

    private final EmailAuthService emailAuthService;

    @Autowired
    public EmailAuthController(EmailAuthService emailAuthService) {
        this.emailAuthService = emailAuthService;
    }

    @PostMapping("/send")
    public ResponseEntity<ApiResponse> sendEmailController(@RequestBody EmailRequestDTO emailRequestDTO) throws IOException {
        ApiResponse response = new ApiResponse();
        try {
            if (emailRequestDTO.getEmail() == null || emailRequestDTO.getEmail().isEmpty()) {
                throw new IllegalArgumentException("이메일을 입력해주세요.");
            } else if (!isValidEmail(emailRequestDTO.getEmail())) {
                throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
            }

            emailAuthService.sendAuthEmail(emailRequestDTO);
            response.setSuccess(true);
            response.setMessage("인증번호가 발송되었습니다.");
        } catch (IOException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        } catch (IllegalArgumentException e) {
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse> verifyCode(@RequestBody AuthCodeDTO authCodeDTO) {
        ApiResponse response = new ApiResponse();
        if(authCodeDTO.getEmail() == null || authCodeDTO.getEmail().isEmpty()) {
            response.setSuccess(false);
            response.setMessage("이메일을 입력해주세요.");
            return ResponseEntity.ok(response);
        }
        if(authCodeDTO.getAuth_code() == null || authCodeDTO.getAuth_code().isEmpty()) {
            response.setSuccess(false);
            response.setMessage("인증번호를 입력해주세요.");
            return ResponseEntity.ok(response);
        }
        boolean isVerified = emailAuthService.verifyAuthCode(authCodeDTO);

        if (!isVerified) {
            response.setSuccess(false);
            response.setMessage("일치하지 않거나 유효하지 않은 코드입니다.");
            return ResponseEntity.ok(response);
        }
        response.setSuccess(true);
        response.setMessage("인증되었습니다.");

        return ResponseEntity.ok(response);
    }

}
