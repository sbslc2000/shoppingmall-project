package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryDto;
import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.cau.shoppingmall.exception.notfound.NoInquiryTypeFoundException;
import org.cau.shoppingmall.exception.notfound.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
public interface OneToOneInquiryService {

    List<OneToOneInquiryDto> getByUserId(Long userId);
    OneToOneInquiry create(OneToOneInquiryForm form, Long userId, List<MultipartFile> multipartFileList) throws IOException, UserNotFoundException, NoInquiryTypeFoundException;
    OneToOneInquiryDto get(Long inquiryId);
    /*
    * 관리자 전용
    * */
    List<OneToOneInquiryDto> getAllInquiries();

    void delete(Long inquiryId);
}
