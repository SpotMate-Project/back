package capstone.project.SpotMate.controller;


import capstone.project.SpotMate.configure.utils.response.InquirySaveResponse;
import capstone.project.SpotMate.configure.utils.response.ReviewResponse;
import capstone.project.SpotMate.configure.utils.response.ReviewSaveResponse;
import capstone.project.SpotMate.dto.ReviewDTO;
import capstone.project.SpotMate.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    private ReviewService reviewService;
    @Autowired
    public ReviewController(ReviewService reviewService){
        this.reviewService=reviewService;
    }
    
    @PostMapping("/save")
    public ResponseEntity<ReviewSaveResponse> createReview (@RequestBody ReviewDTO reviewDTO) {
        ReviewSaveResponse apiResponse = new ReviewSaveResponse();

        try {
            reviewService.saveReview(reviewDTO);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("리뷰가 성공적으로 저장되었습니다.");
            apiResponse.setData(Collections.singletonList(reviewDTO));
            System.out.println(reviewDTO);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("리뷰저장에 실패하였습니다.");
            System.out.println(reviewDTO);
        }
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/get")
    public ResponseEntity<ReviewResponse> AllReview(){
        ReviewResponse apiResponse = new ReviewResponse();

        List<ReviewDTO> reviews = reviewService.getAll(new ReviewDTO(null,null,null,null,null,null));
        if( reviews != null && !reviews.isEmpty()){
            apiResponse.setSuccess(true);
            apiResponse.setData(reviews);
        } else{
            apiResponse.setSuccess(false);
            apiResponse.setData(new ArrayList<>());
        }
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{review_id}")
    public ResponseEntity<ReviewResponse> readone (@PathVariable int review_id){
        List<ReviewDTO> reviews = reviewService.getone(new ReviewDTO(review_id,null,null,null,null,null));

        ReviewResponse reviewResponse = new ReviewResponse();
        if(reviews != null && !reviews.isEmpty()){
            reviewResponse.setSuccess(true);
            reviewResponse.setData(reviews);
        }else{
            reviewResponse.setSuccess(false);
            reviewResponse.setData(new ArrayList<>());
        }

        return ResponseEntity.ok(reviewResponse);
    }

    @GetMapping("/myreview/{user_id}")
    public ResponseEntity<ReviewResponse> myreviw (@PathVariable int user_id){
        List<ReviewDTO> reviews = reviewService.getreview(new ReviewDTO(null,user_id,null,null,null,null));

        ReviewResponse reviewResponse = new ReviewResponse();
        if(reviews != null && !reviews.isEmpty()){
            reviewResponse.setSuccess(true);
            reviewResponse.setData(reviews);
        }else{
            reviewResponse.setSuccess(false);
            reviewResponse.setData(new ArrayList<>());
        }

        return ResponseEntity.ok(reviewResponse);
    }
}
