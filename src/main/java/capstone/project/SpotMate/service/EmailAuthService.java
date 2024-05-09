package capstone.project.SpotMate.service;


import capstone.project.SpotMate.configure.utils.Datetime;
import capstone.project.SpotMate.configure.utils.response.ApiResponse;
import capstone.project.SpotMate.configure.utils.response.SendGridClient;
import capstone.project.SpotMate.dto.AuthCodeDTO;
import capstone.project.SpotMate.dto.EmailRequestDTO;
import capstone.project.SpotMate.mapper.EmailAuthMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EmailAuthService {

    private final EmailAuthMapper emailAuthMapper;
    private final SendGridClient sendGridClient;

    @Autowired
    public EmailAuthService(EmailAuthMapper emailAuthMapper, SendGridClient sendGridClient) {
        this.emailAuthMapper = emailAuthMapper;
        this.sendGridClient = sendGridClient;
    }

    public void sendAuthEmail(EmailRequestDTO emailRequestDTO) throws IOException {
        String authCode = generateAuthCode();

        boolean emailSent = sendGridClient.sendEmail(emailRequestDTO.getEmail(), authCode);

        if (emailSent) {
            emailAuthMapper.invalidateExistingCodes(emailRequestDTO.getEmail());
            emailAuthMapper.saveAuthCode(new AuthCodeDTO(emailRequestDTO.getEmail(), authCode));
        } else {
            ApiResponse response = new ApiResponse();
            response.setSuccess(false);
            response.setMessage("이메일 전송에 실패했습니다.");
            throw new IOException("이메일 전송에 실패했습니다.");
        }
    }

    private String generateAuthCode() {
        return RandomStringUtils.randomNumeric(6);
    }


    public boolean verifyAuthCode(AuthCodeDTO authCodeDTO) {
        AuthCodeDTO savedCode = emailAuthMapper.getAuthCodeInfo(authCodeDTO.getEmail());
        if (savedCode == null) {
            return false;
        }

        boolean isCodeValid = savedCode.getAuth_code().equals(authCodeDTO.getAuth_code());
        boolean isCodeNotExpired = savedCode.getCreated_at().plusMinutes(5).isAfter(Datetime.nowInKst());

        if (isCodeValid && isCodeNotExpired) {
            emailAuthMapper.updateState(authCodeDTO.getEmail(), 1);
        }

        return isCodeValid && isCodeNotExpired;
    }


}
