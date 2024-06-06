package capstone.project.SpotMate.configure.utils.response;

import capstone.project.SpotMate.dto.InquiryDTO;
import capstone.project.SpotMate.dto.ReviewDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewSaveResponse {
    private boolean success;

    private String Message;

    private List<ReviewDTO> data;
}
