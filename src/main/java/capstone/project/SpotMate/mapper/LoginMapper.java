package capstone.project.SpotMate.mapper;

import capstone.project.SpotMate.dto.LoginDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoginMapper {

    LoginDTO findUserByEmail(String email);

}
