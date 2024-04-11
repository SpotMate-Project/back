package capstone.project.SpotMate.controller;

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
