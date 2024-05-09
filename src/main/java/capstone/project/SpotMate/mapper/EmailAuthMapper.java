package capstone.project.SpotMate.mapper;


import capstone.project.SpotMate.dto.AuthCodeDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmailAuthMapper {
    void saveAuthCode(AuthCodeDTO authCodeDTO);
    void invalidateExistingCodes(String email);

    AuthCodeDTO getAuthCodeInfo(String email);

    void updateState(String email, int state);


}
