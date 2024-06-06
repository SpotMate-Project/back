package capstone.project.SpotMate.mapper;

import capstone.project.SpotMate.dto.ReviewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ReviewMapper {
    void insertReview(ReviewDTO reviewDTO);
    List<ReviewDTO> getall(ReviewDTO reviewDTO);
    List<ReviewDTO> findbyuserid(int user_id);

    void update(ReviewDTO reviewDTO);

    void deleteReview(Integer review_id);

    List<ReviewDTO> findbyreviewid(int review_id);
}
