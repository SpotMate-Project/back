package capstone.project.SpotMate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDTO {

    public Integer inquiry_id;
    public Integer user_id ;

    public String title;

    public String body ;

    public String repeat ;
}
