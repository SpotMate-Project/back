package capstone.project.SpotMate.service;

import capstone.project.SpotMate.dto.ReviewDTO;
import capstone.project.SpotMate.mapper.ReviewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewMapper reviewMapper;

    public void saveReview(ReviewDTO reviewDTO){reviewMapper.insertReview(reviewDTO);}

    public List<ReviewDTO> getreview(ReviewDTO reviewDTO){
        return reviewMapper.findbyuserid(reviewDTO.getUser_id());
    }

    public List<ReviewDTO> getone(ReviewDTO reviewDTO) { return reviewMapper.findbyreviewid(reviewDTO.getReview_id());}
    public List<ReviewDTO> getAll(ReviewDTO reviewDTO){
        return reviewMapper.getall(reviewDTO);
    }
}
