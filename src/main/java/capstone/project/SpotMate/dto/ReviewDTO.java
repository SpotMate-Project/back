package capstone.project.SpotMate.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReviewDTO {

    private Integer review_id;

    private Integer user_id;

    private String title;

    private String body;

    private LocalDate created_at;

    private LocalDate modified_at;
}
