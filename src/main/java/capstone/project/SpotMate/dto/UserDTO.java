package capstone.project.SpotMate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@Data
public class UserDTO {

    private Integer id;

    private String email;

    private String password;

    private String name;

    private String ph_num;

    private String address;

}
