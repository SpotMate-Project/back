package capstone.project.SpotMate.mapper;

import capstone.project.SpotMate.dto.InquiryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InquiryMapper {

    void insertInquiry(InquiryDTO inquiryDTO);

    List<InquiryDTO> findbyuserid(int user_id);

    void repeatInquiry(InquiryDTO inquiryDTO);

    List<InquiryDTO> getAll(InquiryDTO inquiryDTO);

    List<InquiryDTO> findbyInquiryid(int inquiry_id);
}
