package capstone.project.SpotMate.service;

import capstone.project.SpotMate.configure.PasswordEncoderSHA512;
import capstone.project.SpotMate.dto.LoginDTO;
import capstone.project.SpotMate.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    PasswordEncoderSHA512 passwordEncoderSHA512;

    public boolean checkLogin(LoginDTO loginDTO){
        LoginDTO userDB = loginMapper.findUserByEmail(loginDTO.getEmail());
        if(userDB!=null){
            return passwordEncoderSHA512.matches(loginDTO.getEmail().concat(loginDTO.getPassword()) , userDB.getPassword());
        }
        return false;
    }
}
