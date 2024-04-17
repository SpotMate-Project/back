package capstone.project.SpotMate.service;

import capstone.project.SpotMate.configure.PasswordEncoderSHA512;
import capstone.project.SpotMate.dto.UserDTO;
import capstone.project.SpotMate.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoderSHA512 passwordEncoderSHA512;

    public UserService(UserMapper userMapper , PasswordEncoderSHA512 passwordEncoderSHA512) {
        this.userMapper = userMapper;
        this.passwordEncoderSHA512 = passwordEncoderSHA512;
    }

    public boolean isEmailDuplicated(String email){
        return userMapper.emailExists(email);
    }

    public boolean isNicknameDuplicated(String nickname){
        return userMapper.nicknameExists(nickname);
    }

    public void signup(UserDTO user){
        String plaintext = user.getEmail() + user.getPassword();
        String hashpassword = passwordEncoderSHA512.encode(plaintext);
        user.setPassword(hashpassword);
        userMapper.signup(user);
    }

}
