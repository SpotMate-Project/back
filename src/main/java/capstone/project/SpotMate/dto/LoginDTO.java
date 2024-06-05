package capstone.project.SpotMate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    public Integer id= null;

    public String email =null;

    public String password =null;

    public int state;
}
