package capstone.project.SpotMate.controller;

import capstone.project.SpotMate.configure.utils.Validation;
import capstone.project.SpotMate.configure.utils.response.ApiResponse;
import capstone.project.SpotMate.configure.utils.response.UserResponse;
import capstone.project.SpotMate.dto.LoginDTO;
import capstone.project.SpotMate.dto.UserDTO;
import capstone.project.SpotMate.dto.UserInfoDTO;
import capstone.project.SpotMate.service.LoginService;
import capstone.project.SpotMate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/validation/nickname")
    public ResponseEntity<ApiResponse> validatenickname(@RequestBody UserDTO user){
        ApiResponse apiResponse = new ApiResponse();
        if(!Validation.isValidNickname(user.getNickname())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("닉네임은 한글이나 영문 또는 숫자 조합으로 3~8자 이어야 합니다.");
        } else if (userService.isNicknameDuplicated(user.getNickname())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("이미 등록된 닉네임입니다.");
        } else{
            apiResponse.setSuccess(true);
            apiResponse.setMessage("사용 가능한 닉네임입니다.");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginDTO user ){
        ApiResponse apiResponse = new ApiResponse();
        if(loginService.checkLogin(user)){
            apiResponse.setSuccess(true);
            apiResponse.setMessage("로그인 성공");
        }
        else {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("이메일 또는 비밀번호가 일치하지않습니다.");
        }
        return ResponseEntity.ok(apiResponse);
    }


    @PostMapping("/info")
    public ResponseEntity<UserResponse> userinfo(@RequestBody UserInfoDTO userInfoDTO){
        List<UserInfoDTO> infos = userService.getinfo(new UserInfoDTO(null,userInfoDTO.getEmail(),null ,null));

        UserResponse userResponse = new UserResponse();
        if(infos != null && !infos.isEmpty()){
            userResponse.setSuccess(true);
            userResponse.setData(infos);
        } else {
            userResponse.setSuccess(false);
        }
        return ResponseEntity.ok(userResponse);
    }
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> signup(@RequestBody UserDTO user){
        ApiResponse apiResponse = new ApiResponse();
        if(!Validation.isValidEmail(user.getEmail())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("유효하지 않는 이메일 형식입니다.");
        } else if (!Validation.isValidPassword(user.getPassword())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("비밀번호는 영문,숫자,특수문자의 조합으로 8자 이상이어야 합니다.");
        } else if (!Validation.isValidNickname(user.getNickname())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("닉네임은 한글이나 영문 또는 숫자 조합으로 3~8자 이어야합니다.");
        } else if (userService.isEmailDuplicated(user.getEmail())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("이미 등록된 이메일입니다.");
        } else if (userService.isNicknameDuplicated(user.getNickname())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("이미 사용중인 닉네입니다.");
        } else if (!Validation.PasswordCheck(user.getPassword(), user.getPasswordCheck())){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("비밀번호와비밀번호확인이 일치하지 않습니다");
        } else{
            userService.signup(user);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("회원가입이 완료되었습니다.");
        }
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateUserinfo(@RequestBody UserInfoDTO userInfoDTO){
        ApiResponse apiResponse = new ApiResponse();
        try{
            userService.updateUserinfo(userInfoDTO);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("유저 정보 수정이 완료되었습니다.");
        } catch (Exception e){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("유저 정보 수정에 실패하였습니다.");
        }
        return ResponseEntity.ok(apiResponse);
    }

}
