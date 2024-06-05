package capstone.project.SpotMate.configure.utils.response;

import capstone.project.SpotMate.dto.InquiryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InquirySaveResponse {

    private boolean success;

    private String Message;

    private List<InquiryDTO> data;
}
