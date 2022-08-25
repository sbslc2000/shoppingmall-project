package org.cau.shoppingmall.service;

import org.cau.shoppingmall.dto.inquiry.OneToOneInquiryForm;
import org.cau.shoppingmall.entity.inquiry.OneToOneInquiry;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
public interface OneToOneInquiryService {


    OneToOneInquiry create(OneToOneInquiryForm form, Long userId, List<MultipartFile> multipartFileList) throws IOException;
}
