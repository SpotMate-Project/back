package capstone.project.SpotMate.mapper;

import capstone.project.SpotMate.dto.UserDTO;
import capstone.project.SpotMate.dto.UserInfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
   void signup(UserDTO user);

   boolean emailExists(String email);
   boolean nicknameExists(String nickname);

   List<UserInfoDTO> finduserinfo(String email);
}
