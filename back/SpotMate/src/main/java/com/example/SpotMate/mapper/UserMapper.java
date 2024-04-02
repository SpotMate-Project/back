package com.example.SpotMate.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.SpotMate.dto.UserDTO;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
   void signup(UserDTO user);

}
