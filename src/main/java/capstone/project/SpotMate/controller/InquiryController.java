package capstone.project.SpotMate.controller;

import capstone.project.SpotMate.configure.utils.response.InquirySaveResponse;
import capstone.project.SpotMate.dto.InquiryDTO;
import capstone.project.SpotMate.mapper.InquiryMapper;
import capstone.project.SpotMate.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class InquiryController {

    private InquiryService inquiryService;

    @Autowired
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService=inquiryService;
    }

    @PostMapping("/Inquiry")
    public ResponseEntity<InquirySaveResponse> createInquiry (@RequestBody InquiryDTO inquiryDTO){
        InquirySaveResponse apiResponse = new InquirySaveResponse();

        try{
            inquiryService.saveInquiry(inquiryDTO);
            apiResponse.setSuccess(true);
            apiResponse.setMessage("문의가 성공적으로 저장되었습니다.");
            apiResponse.setData(Collections.singletonList(inquiryDTO));
        } catch (Exception e){
            apiResponse.setSuccess(false);
            apiResponse.setMessage("문의 저장에 실패하였습니다.");
        }
        return ResponseEntity.ok(apiResponse);
    }
}
