package capstone.project.SpotMate.service;

import capstone.project.SpotMate.dto.InquiryDTO;
import capstone.project.SpotMate.mapper.InquiryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InquiryService {

    @Autowired
    private InquiryMapper inquiryMapper;

    public void saveInquiry (InquiryDTO inquiryDTO)
    {inquiryMapper.insertInquiry(inquiryDTO);}

    public List<InquiryDTO> findbyuserid(InquiryDTO inquiryDTO){
        return inquiryMapper.findbyuserid(inquiryDTO.getUser_id());
    }

    public void updaterepeat(InquiryDTO inquiryDTO){
        inquiryMapper.repeatInquiry(inquiryDTO);
    }

    public List<InquiryDTO> getInquiryid(InquiryDTO inquiryDTO){
        return inquiryMapper.findbyInquiryid(inquiryDTO.getInquiry_id());
    }
}
