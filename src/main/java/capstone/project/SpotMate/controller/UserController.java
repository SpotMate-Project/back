package capstone.project.SpotMate.controller;

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
