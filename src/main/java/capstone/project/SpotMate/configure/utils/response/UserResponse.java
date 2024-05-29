package capstone.project.SpotMate.configure.utils.response;

import capstone.project.SpotMate.dto.UserInfoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private boolean success;

    private List<UserInfoDTO> data;
}
