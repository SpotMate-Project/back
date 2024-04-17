package capstone.project.SpotMate.controller;

import capstone.project.SpotMate.configure.utils.Validation;
import capstone.project.SpotMate.configure.utils.response.ApiResponse;
import capstone.project.SpotMate.dto.UserDTO;
import capstone.project.SpotMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("validation/email")
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
