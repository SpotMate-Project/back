package capstone.project.SpotMate.configure.utils.response;

import capstone.project.SpotMate.dto.InquiryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquiryResponse {
    private boolean success;

    private List<InquiryDTO> data;
}
