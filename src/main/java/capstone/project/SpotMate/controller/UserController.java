package capstone.project.SpotMate.controller;

import capstone.project.SpotMate.configure.utils.Validation;
import capstone.project.SpotMate.configure.utils.response.ApiResponse;
import capstone.project.SpotMate.dto.LoginDTO;
import capstone.project.SpotMate.dto.UserDTO;
import capstone.project.SpotMate.service.LoginService;
import capstone.project.SpotMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    private LoginService loginService;
    @Autowired
    public UserController(UserService userService, LoginService loginService){
        this.userService=userService;
        this.loginService=loginService;
    }


    @PostMapping("/validation/email")
    public ResponseEntity<ApiResponse> validateEmail(@RequestBody UserDTO user){
        ApiResponse apiResponse = new ApiResponse();
        if(!Validation.isValidEmail(user.getEmail())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("이메일 형식을 확인하여주세요.");
        } else if (userService.isEmailDuplicated(user.getEmail())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("이미 등록된 이메일입니다.");
        }
        else {
            apiResponse.setSuccess(true);
            apiResponse.setMessage("사용 가능한 이메일입니다.");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/validation/password")
    public ResponseEntity<ApiResponse> validatePassword(@RequestBody UserDTO user){
        ApiResponse apiResponse = new ApiResponse();
        if(!Validation.isValidPassword(user.getPassword())){
            apiResponse.setSuccess(true);
            apiResponse.setMessage("비밀번호는 영문,숫자,특수문자의 조합으로 8자 이상이어야 합니다.");
        }else{
            apiResponse.setSuccess(true);
            apiResponse.setMessage("유효한 비밀번호입니다.");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/validation/passwordCheck")
    public ResponseEntity<ApiResponse> checkPassword(@RequestBody UserDTO user){
        ApiResponse apiResponse = new ApiResponse();
        if(!Validation.PasswordCheck(user.getPassword(),user.getPasswordCheck())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("비밀번호가 일치하지 않습니다.");
        } else{
            apiResponse.setSuccess(true);
            apiResponse.setMessage("비밀번호 일치!!");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDTO user ){
        ApiResponse apiResponse = new ApiResponse();
        if(loginService.checkLogin(user)){
            apiResponse.setSuccess(true);
            apiResponse.setMessage("로그인 성공");
        } else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("이메일 또는 비밀번호가 일치하지않습니다.");
        }
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody UserDTO user){
        ApiResponse apiResponse = new ApiResponse();
        try {
            userService.signup(user);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("회원가입이 완료되었습니다.");
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("회원가입에 실패하였습니다.");
        }
        return ResponseEntity.ok(apiResponse);
    }

}
