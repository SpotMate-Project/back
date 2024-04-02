package capstone.project.SpotMate.mapper;

import capstone.project.SpotMate.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
   void signup(UserDTO user);

}
